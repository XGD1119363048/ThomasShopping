<template>
<div >
    <div class="title">热门搜索</div>
    <ul class="menu">
        <li @click="menu1">瓜子</li>
        <li @click="menu2">辣条</li>
        <li>凤爪</li>
        <li>泡面</li>
        <li>巧克力</li>
        <li>葡萄干</li>
        <li>薯片</li>
        <li>糖果</li>
        <li>肉脯</li>
        <li>牛奶</li>
        <li>特产</li>
    </ul>
</div>
</template>

<style type="text/css" >

.title{
    width:150px;
    background-color:rgb(176,176,176);
    list-style: none;
    color: rgb(51,51,51);
    justify-content: center;
    display:flex;
    
}

.menu li{
    list-style: none;
    height:50px;
    background-color: #f5f5f5;
    margin-bottom: 2px;
    line-height: 50px;
    cursor:pointer;
    justify-content: center;
    display:flex;

    

}

</style>

<script type="text/javascript">
import Msg from "../../msg.js";
export default{
    methods:{
        menu1:function(){
            Msg.$emit("val","1");
            },
        menu2:function(){
        Msg.$emit("val","2");
        }

        }

    }

</script>