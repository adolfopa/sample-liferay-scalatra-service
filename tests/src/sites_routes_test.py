from util import url, default_headers

import json
import requests

def gets_all_available_sites_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'sites'), headers=default_headers())
    assert r.status_code == 200
    assert len(r.json()) > 0

def gets_a_site_detail_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'sites'), headers=default_headers())
    group_id = next(g['classPK'] for g in r.json() if g['friendlyURL'] == '/guest')

    r = requests.get(url('instances', company_id, 'sites', group_id),
                     headers=default_headers())
    assert r.status_code == 200
    assert r.json()['classPK'] == group_id

def posts_a_new_site_and_then_deletes_it_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.post(url('instances', company_id, 'sites'),
                      headers=default_headers(),
                      data=json.dumps({
                          'userId': 2,
                          'className': None,
                          'classPK': 0,
                          'liveGroupId': 0,
                          'name': 'foo',
                          'description': 'bar',
                          'groupType': 1,
                          'friendlyURL': '/foo',
                          'site': True,
                          'active': False
                      }))

    group_id = r.json()['classPK']
    r = requests.delete(url('instances', company_id, 'sites', group_id),
                        headers=default_headers())

    assert r.status_code == 200

def deleting_a_non_existent_site_fails_test():
    r = requests.delete(url('instances', 1, 'sites', 10000),
                        headers=default_headers())
    assert r.status_code == 404

def getting_a_non_existent_site_fails_test():
    r = requests.get(url('instances', 1, 'sites', 10000),
                     headers=default_headers())
    assert r.status_code == 404
