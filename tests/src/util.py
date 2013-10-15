URL = 'http://localhost:8080/scalatra-sample'

def url(*args):
    return '{0}/{1}'.format(URL, '/'.join(str(p) for p in args))

def default_headers():
    return {'content-type': 'application/json'}
