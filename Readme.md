一个舞萌DX歌曲查询接口(开发中)

目前已实现查歌服务与别名服务

可以访问 https://maimai.ohara-rinne.tech/doc.html 查看与调试已完成的接口文档

接口地址: https://maimai.ohara-rinne.tech/api

使用的技术栈：Spring, Spring Data JPA, Spring MVC, Spring Boot, Spring Cloud

# 开放的接口

# 查歌接口


## 条件查询


**接口地址**:`/api/music/query/{page}/{size}`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "artist": "",
  "bpm": 0,
  "difficulty": "",
  "genre": "",
  "innerLevel": {
    "from": 0,
    "to": 0
  },
  "isNew": true,
  "level": {
    "from": "",
    "to": ""
  },
  "title": "",
  "type": "",
  "version": ""
}
```


**请求参数**:


| 参数名称                         | 参数说明       | 请求类型 | 是否必须  | 数据类型            | 
|------------------------------|------------|------|-------|-----------------|
| conditions                   | 查询条件，可随意组合 | body | true  | QueryConditions | 


**响应参数**:


| 参数名称    | 参数说明 | 类型             |
|---------|------|----------------|
| success |      | boolean        |
| code    |      | integer        |
| message |      | string         |
| data    |      | PageDTO«Music» |

**响应示例**:

```json
{
    "success": true,
        "code": 200,
        "message": "success",
        "data": {
        "total": 719,
            "data": [
            {
                "id": 8,
                "title": "True Love Song",
                "artist": "Kai/クラシック「G線上のアリア」",
                "type": "SD",
                "version": "maimai",
                "bpm": 150,
                "isNew": false,
                "genre": "maimai",
                "coverUrl": null,
                "level": {
                    "basic": "4",
                    "advanced": "6",
                    "expert": "9",
                    "master": "11+",
                    "remaster": null
                },
                "innerLevel": {
                    "basic": 4,
                    "advanced": 6.4,
                    "expert": 9.2,
                    "master": 11.7,
                    "remaster": null
                },
                "charts": null,
                "alias": []
            }
        ]
    }
}
```

## 获取乐曲信息


**接口地址**:`/api/music/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称   | 参数说明         | 请求类型 | 是否必须 | 数据类型    |
| ---------- | ---------------- | -------- | -------- | ------- | 
| id         | 乐曲id           | path     | true     | integer |
| withCharts | 是否包含谱面信息 | query    | false    | boolean |


**响应参数**:


| 参数名称    | 参数说明 | 类型      | 
|---------|------|---------|
| success |      | boolean |
| code    |      | integer |
| message |      | string  |
| data    |      | Music   | 


**响应示例**:

```json
{
  "success": true,
  "code": 200,
  "message": "success",
  "data": {
    "id": 8,
    "title": "True Love Song",
    "artist": "Kai/クラシック「G線上のアリア」",
    "type": "SD",
    "version": "maimai",
    "bpm": 150,
    "isNew": false,
    "genre": "maimai",
    "coverUrl": null,
    "level": {
      "basic": "4",
      "advanced": "6",
      "expert": "9",
      "master": "11+",
      "remaster": null
    },
    "innerLevel": {
      "basic": 4,
      "advanced": 6.4,
      "expert": 9.2,
      "master": 11.7,
      "remaster": null
    },
    "charts": {
      "basic": {
        "type": "SD",
        "difficulty": "Basic",
        "level": "4",
        "innerLevel": 4,
        "tap": 63,
        "hold": 23,
        "slide": 8,
        "touch": null,
        "break": 2,
        "total": 96,
        "designer": "-",
        "playerCount": 145,
        "average": 100.41469241379312,
        "tag": "Very Easy",
        "difficultyRankInSameLevel": 13,
        "songCountInSameLevel": 334,
        "ssscount": 139
      },
      "advanced": {
        "type": "SD",
        "difficulty": "Advanced",
        "level": "6",
        "innerLevel": 6.4,
        "tap": 85,
        "hold": 27,
        "slide": 6,
        "touch": null,
        "break": 4,
        "total": 122,
        "designer": "-",
        "playerCount": 159,
        "average": 99.52002327044023,
        "tag": "Medium",
        "difficultyRankInSameLevel": 112,
        "songCountInSameLevel": 330,
        "ssscount": 126
      },
      "expert": {
        "type": "SD",
        "difficulty": "Expert",
        "level": "9",
        "innerLevel": 9.2,
        "tap": 110,
        "hold": 56,
        "slide": 9,
        "touch": null,
        "break": 2,
        "total": 177,
        "designer": "譜面-100号",
        "playerCount": 356,
        "average": 97.5149441011236,
        "tag": "Medium",
        "difficultyRankInSameLevel": 50,
        "songCountInSameLevel": 98,
        "ssscount": 148
      },
      "master": {
        "type": "SD",
        "difficulty": "Master",
        "level": "11+",
        "innerLevel": 11.7,
        "tap": 263,
        "hold": 14,
        "slide": 19,
        "touch": null,
        "break": 6,
        "total": 302,
        "designer": "ニャイン",
        "playerCount": 1120,
        "average": 99.30195812499996,
        "tag": "Easy",
        "difficultyRankInSameLevel": 24,
        "songCountInSameLevel": 127,
        "ssscount": 640
      },
      "remaster": null
    },
    "alias": []
  }
}

```


# 谱面查询接口


## 获取指定乐曲的指定谱面


**接口地址**:`/api/chart/{id}/{difficulty}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称       | 参数说明    | 请求类型 | 是否必须 | 数据类型    | 
|------------|---------|------|------|---------|
| id         | 歌曲id    | path | true | integer | 
| difficulty | 难度，从0-4 | path | true | integer | 


**响应参数**:


| 参数名称                              | 参数说明 | 类型      |
| ------------------------------------- | -------- |---------| 
| success |      | boolean |
| code    |      | integer |
| message |      | string  |
| data    |      | Chart   |

**响应示例**:

```json
{
    "success": true,
        "code": 200,
        "message": "success",
        "data": {
        "type": "SD",
            "difficulty": "Master",
            "level": "11+",
            "innerLevel": 11.7,
            "tap": 263,
            "hold": 14,
            "slide": 19,
            "touch": null,
            "break": 6,
            "total": 302,
            "designer": "ニャイン",
            "playerCount": 1120,
            "average": 99.30195812499996,
            "tag": "Easy",
            "difficultyRankInSameLevel": 24,
            "songCountInSameLevel": 127,
            "ssscount": 640
    }
}
```

# 乐曲别名服务


## 根据别名获取乐曲

**接口地址**:`/api/alias/query/{alias}`

**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:

**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型 |
| -------- | -------- | -------- | -------- | -------- |
| alias    | alias    | path     | true     | string   |




**响应参数**:


| 参数名称 | 参数说明 | 类型           |
| -------- | -------- | -------------- |
| code     |          | integer(int32) |
| data     |          | array          |
| success  |          | boolean        |
| message | | string | 

**响应示例**:

```javascript
{
    "success": true,
        "code": 200,
        "message": "success",
        "data": [
        {
            "musicId": 627,
            "alias": "哨戒班ll"
        },
        {
            "musicId": 11198,
            "alias": "明日的夜空哨戒班"
        },
        {
            "musicId": 507,
            "alias": "哨戒班"
        }
    ]
}
```


## 


## 根据乐曲id获取别名

**接口地址**:`/api/alias/{music_id}`

**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`

**接口描述**:

**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       |
| -------- | -------- | -------- | -------- | -------------- |
| music_id | music_id | path     | true     | integer(int32) |
| alias | 别名 | query | true | string



**响应参数**:


| 参数名称 | 参数说明 | 类型           |
| -------- |------| -------------- |
| code     |      | integer(int32) |
| data     | 别名列表 | array          |
| message  |      | string         |
| success  |      | boolean        |


**响应示例**:

```json
{
  "success": true,
  "code": 200,
  "message": "success",
  "data": [
    "烧鸡班",
    "哨戒班",
    "明日夜空的哨戒班"
  ]
}
```


## 添加别名

**接口地址**:`/api/alias/{music_id}`

**请求方式**:`POST`

**请求数据类型**:`application/json`

**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型  | 是否必须 | 数据类型       |
| -------- | -------- |-------| -------- | -------------- |
| alias    | alias    | query | true     | string         |
| music_id | music_id | path  | true     | integer(int32) |

**响应参数**:


| 参数名称 | 参数说明 | 类型           |
| -------- | -------- | -------------- |
| code     |          | integer(int32) |
| success  |          | boolean        |
| message  |          | string         |

**响应示例**:

```javascript
{
  "success": true,
  "code": 200,
  "message": "已添加到待审核列表"
}
```