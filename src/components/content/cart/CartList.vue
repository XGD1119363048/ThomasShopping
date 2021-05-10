<template>
  <div id="CartList">
    <div v-if="status == 0 && goods.length > 0">
      <cart-list-item v-for="item in goods"
                      :goods-item="item"
                      :key="item.id"
                      class="good-item"
                      @minus-click="minusClick(item)"
                      @plus-click="plusClick(item)"
                      @delete-click="deleteClick(item)"
      />
    </div>
    <div v-else class="empty">
      Your cart is empty!
    </div>
  </div>
</template>

<script>
  import CartListItem from "components/content/cart/CartListItem";

  export default {
    name: "CartList",
    components: {
      CartListItem
    },
    props: {
      goods: {
        type: Array,
        default() {
          return []
        }
      },
      status: {
        type: Number,
        default: 0
      }
    },
    methods: {
      minusClick(item) {
        // item.count--
        this.$emit('minus-click', item)
      },
      plusClick(item) {
        // item.count--
        this.$emit('plus-click', item)
      },
      deleteClick(item) {
        this.$emit('delete-click', item)
      }
    }
  }
</script>

<style scoped>
  .good-item {
    margin: 1px 300px;
    border: 1px solid gray;
  }

  .empty {
    margin: 10px 300px;
    text-align: center;
    line-height: 100px;
    font-size: 25px;
    color: black;
  }
</style>
