# 软件工程实训报告
## 代码结构分析
### 前端设计

### 后端 有关SpringBoot的设计
#### Controller：
1. GameController：调用业务层方法来控制游戏逻辑; controller层的功能为本次游戏请求和响应控制; controller层负责本次前后端交互，接受前端请求，，接收返回的数据，最后返回具体的页面和数据到客户端。
#### entity:
1. ItemEntity:设计物品类，赋予物品名称、物品重量、物品id、所有者类型以及所有者id这些相关属性参与游戏交互。
2. PlayerEntity:设计玩家类，玩家默认设计只有一个人，赋予玩家id，玩家携带最大重量以及上一个到达的房间有关属性。
3. RoomEntity:设计房间类，设计相关属性和方法。
#### model:
1. GetPlayerInfoResponse:整理响应请求玩家所需要的信息
2. GetRoomInfoResponse:整理响应请求房间所需要的信息
3. Status:返回的响应信息
4. StatusResponse:该类用于整理返回的响应信息
#### service:
1. PlayerService:
2. RoomService:
#### store:
1. ItemStore:
2. PathStore:
3. RoomStore:


### 扩展功能(具体实现见代码文件)
