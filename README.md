# lucky-ms

#### 关于 Json
* 内置 4 种 json

##### FastJSON
* 用来打印日志，支持3种形式的脱敏，详见@Desensitized
* 用来在 Lettuce 种序列化。所以在任何地方，都不要使用@JSONField(serialize = false)

##### Gson
* 用于在 Feign 中编码解码，当一个 controller 中的方法，被注解为@Api（internal）时，此 Rest 接口将使用 Gson 编解码

##### Jackson
* 用于在@Api（public）（默认的）的 Rest 接口中编解码


##### jsoniter
* 只引入，未使用

