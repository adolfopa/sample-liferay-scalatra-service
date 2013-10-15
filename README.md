# Sample Liferay Scalatra Service #

## Build & Run ##

```sh
$ cd Sample_Liferay_Scalatra_Service
$ ./sbt
> package
> exit
$ cp target/scala-2.10/*.war ${LRHOME}/webapps/scalatra-sample.war
$ curl -H 'Content-Type: application/json' http://localhost:8080/scalatra-sample/instances
```
## Examples ##

In the `tests` directory there is a set of test cases showing all valid use cases. To run:
```sh
$ cd tests
$ virtualenv .
$ source ./bin/activate.sh
$ pip install nose
$ pip install requests
$ ./bin/nosetests -w src -v
```

Enjoy!

