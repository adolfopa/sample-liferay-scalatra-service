from util import url, default_headers

import json
import requests

def gets_all_users_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'users'), headers=default_headers())
    assert r.status_code == 200
    assert len(r.json()) > 0

def gets_a_user_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'users'), headers=default_headers())
    user_id = next(u['userId'] for u in r.json() if u['screenName'] == 'test')

    r = requests.get(url('instances', company_id, 'users', user_id),
                     headers=default_headers())
    assert r.status_code == 200
    assert r.json()['userId'] == user_id

def getting_a_non_existent_user_fails_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'users'), headers=default_headers())
    user_id = next(u['userId'] for u in r.json() if u['screenName'] == 'test')

    r = requests.get(url('instances', company_id, 'users', int(user_id) + 1),
                     headers=default_headers())
    assert r.status_code == 404

def creates_and_deletes_a_user_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.post(url('instances', company_id, 'users'), headers=default_headers(),
                      data=json.dumps({
                          'userId': 0,
                          'creatorUserId': 2,
                          'password': 'test',
                          'screenName': 'foobar',
                          'email': 'foobar@mail.com',
                          'locale': {'language': 'en'},
                          'firstName': 'Foo',
                          'middleName': 'Bar',
                          'lastName': 'Bar',
                          'male': True,
                          'birthDate': '1972-01-01 03:15:00Z',
                          'jobTitle': 'Unemployed'
                      }))
    assert r.status_code == 200
    user_id = r.json()['userId']

    r = requests.delete(url('instances', company_id, 'users', user_id),
                        headers=default_headers())
    assert r.status_code == 200

def deleting_a_non_existent_user_fails_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'users'),
                     headers=default_headers())
    user_id = next(u['userId'] for u in r.json() if u['screenName'] == 'test')

    r = requests.delete(url('instances', company_id, 'users', int(user_id) + 1),
                        headers=default_headers())
    assert r.status_code == 404
