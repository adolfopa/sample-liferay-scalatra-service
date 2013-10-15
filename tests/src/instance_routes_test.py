from util import url, default_headers
import requests

def returns_at_least_one_instance_test():
    r = requests.get(url('instances'), headers=default_headers())
    assert r.status_code == 200
    assert len(r.json()) > 0

def gets_the_detail_of_an_instance_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id), headers=default_headers())
    assert r.status_code == 200
    assert r.json()['companyId'] == company_id

def getting_a_non_existent_site_fails_test():
    r = requests.get(url('instances', 1, 'sites', 10000), headers=default_headers())
    assert r.status_code == 404
