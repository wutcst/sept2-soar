# world_of_zuul

## Project setup
```
yarn install
```

### Compiles and hot-reloads for development
```
yarn serve
```

### Compiles and minifies for production
```
yarn build
```

### Lints and fixes files
```
yarn lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### 对象
Room: 
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)

Item:
    description,
    weight

### 数据库游戏进度表:
    1、当前房间对象
    2、当前游戏所保留的栈 (格式是[{name,description,boolean[] exits,ArrayList<Item></Item>items}]？)

### 初始化界面，获取起点房间
path: /createGame
method: GET

```json
{
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)
}
```

### 点击方向，传方向给后端，后端匹配后返回房间信息，同时更新数据库
path: /go
method: GET
```json
data:
{
    direction
}
```

```json
{
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)
}
```

### 传送房间，后端响应后随机返回一个房间
path: /transfer
method: GET

```json
{
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)
}
```

### 返回上一个房间，后端返回栈顶房间，同时更新数据库
path: /back
method: GET

```json
{
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)
}
```

### 继续游戏，从数据库获取游戏进度，返回最后一次到达的房间信息，后端用栈存放栈信息
path: /continueGame
method: GET
Body:
```json
{
    name,
    description,
    boolean[] exits,(4位，东南西北)
    ArrayList<Item> items, (存放物品对象)
}
```
