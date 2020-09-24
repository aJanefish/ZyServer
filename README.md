# ZyServer

**个人简单服务器**
## GET
```php
curl http://localhost:3434/okhttp
```

```php
curl http://localhost:3434/okhttp

{"code":200,"msg":"OK","data":"GET from Server,Your Msg is :"}
```

```php
curl -I http://localhost:3434/okhttp

HTTP/1.1 200 OK
Date: Thu, 24 Sep 2020 00:10:47 GMT
Content-Type: application/json; charset=UTF-8
author: zy
Connection: close
Server: Jetty(9.3.2.v20150730)
```


## POST
```php
curl -X POST http://localhost:3434/okhttp
```

```php
curl -X POST http://localhost:3434/okhttp

{"code":200,"msg":"OK","data":"POST from Server,Your Msg is :"}
```