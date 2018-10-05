# AT ANDROID COMIC API DOCUMENT

## Base URL: https://at-android-comic.000webhostapp.com/

### UPLOAD

### Upload image: api/v1/upload/image/

#### Method: POST

#### Params:

|Param|Type|Max Length|Note|Require|
|---|---|---|---|---|
|image|File||Mutil Part|Yes|

#### Result:

```
{
    "image_name": "15368982157977fixed.png"
}

```

## USER

### 1. Sign Up: api/v1/user/signup/signup.php

#### Method: POST

#### Params:

|Param|Type|Max Length|Note|Require|
|---|---|---|---|---|
|email|String|300||Yes|
|password|String|300|Encrypt with SHA256 before|Yes|
|avatar|String|100|Result from api upload image|No|

#### Result:
```
{
    "message": "Tạo tài khoản thành công."
}
```

### 2. Login: api/v1/user/login/login.php

#### Method: POST

|Param|Type|Max Length|Note|Require|
|---|---|---|---|---|
|email|String|300||Yes|
|password|String|300|Encrypt with SHA256 before|Yes|

### Result

```
{
    "access_token": "ff10e81b0021496a2f2b230e448abf41f05994ddbfca1a97997cfd22a8b329a0"
}

```
### 3. Profile: api/v1/user/profile.php

#### Method: GET

|Param|Type|Max Length|Note|Require|
|---|---|---|---|---|
|access-token|String||Header|Yes|

### Result

```
{
     "avatar" : "http://",
     "user-name": "user-name",
     "nick-name": "nick-name",
     "rate-count": 1
}
```

## API EXCEPTION

### Status code: 678

### Model

```
{
    "code": 678,
    "message": "Thiếu dữ liệu."
}
```
