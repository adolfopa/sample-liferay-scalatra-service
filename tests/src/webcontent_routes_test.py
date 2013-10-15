from util import url, default_headers

import json
import requests

def returns_all_site_articles_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'sites'),
                     headers=default_headers())
    group_id = next(g['classPK'] for g in r.json() if g['friendlyURL'] == '/guest')

    r = requests.get(url('instances', company_id, 'sites', group_id, 'articles'),
                     headers=default_headers())
    assert r.status_code == 200
    assert len(r.json()) >= 0

def gets_an_article_detail_test():
    r = requests.get(url('instances'), headers=default_headers())
    company_id = next(c['companyId'] for c in r.json() if c['mx'] == 'liferay.com')

    r = requests.get(url('instances', company_id, 'sites'),
                     headers=default_headers())
    group_id = next(g['classPK'] for g in r.json() if g['friendlyURL'] == '/guest')

    r = requests.get(url('instances', company_id, 'sites', group_id, 'articles'),
                     headers=default_headers())
    article_id = next(a['articleId'] for a in r.json())

    r = requests.get(url('instances', company_id, 'sites', group_id, 'articles', article_id),
                     headers=default_headers())
    assert r.status_code == 200
    assert r.json()['articleId'] == article_id
