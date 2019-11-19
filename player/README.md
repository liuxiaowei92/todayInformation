

## 播放器配置信息
> 如何配置

- 在清单文件中配置meta-data信息
- name值必须为playertype
- value 值 1~4
> 代码示例

        <meta-data
            android:name="playertype"
            android:value="1"/>
            //value=1 :medioPlayer
            //value=2 :ExoPlayer