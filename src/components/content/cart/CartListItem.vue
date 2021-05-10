<template>
  <div id="CartListItem">
    <a-row style="text-align: center; line-height: 150px">
      <a-col :span="2">
        <a-button icon="minus" shape="circle" @click="deleteClick"></a-button>
      </a-col>
      <a-col :span="14" style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap">
        <img :src="goodsItem.imageAddress" width="15%"/>
        {{goodsItem.name}}
      </a-col>
      <a-col :span="4">
        <a-space>
          <a-button icon="minus" @click="minusCount" :disabled="goodsItem.count == 1"></a-button>
          {{goodsItem.count}}
          <a-button icon="plus" @click="plusCount" :disabled="goodsItem.count >= goodsItem.stock"></a-button>
        </a-space>
      </a-col>
      <a-col :span="4" style="color: red; font-size: 20px">
        ￥{{(goodsItem.pricePerCount * goodsItem.count).toFixed(2)}}
      </a-col>
    </a-row>
  </div>
</template>

<script>
  import { Row, Col, Button, Space } from 'ant-design-vue'

  export default {
    name: "CartListItem",
    components: {
      'a-row': Row,
      'a-col': Col,
      'a-button': Button,
      'a-space': Space
    },
    props: {
      goodsItem: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    methods: {
      deleteClick() {
        this.$emit('delete-click')
      },
      minusCount() {
        // this.goodsItem.count--
        this.$emit('minus-click')
      },
      plusCount() {
        // this.goodsItem.count++
        this.$emit('plus-click')
      }
    }
  }
</script>

<style scoped>
  #CartListItem {
    border: 1px solid gray;
  }
</style>
