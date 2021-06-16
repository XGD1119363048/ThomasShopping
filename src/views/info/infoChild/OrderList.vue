<template>
  <div id="order-list">
    <a-table :columns="columns" :data-source="data" class="components-table-demo-nested">
      <a-button slot="operation" slot-scope="record" :disabled="record.status != 1" @click="cancelOrder(record.orderId, record.index)">Cancel</a-button>
      <a-table
          slot="expandedRowRender"
          slot-scope="text"
          :columns="innerColumns"
          :data-source="innerData"
          :pagination="false"
      >
        <span slot="status" slot-scope="text"> <a-badge status="success" />Finished </span>
      </a-table>
    </a-table>
  </div>
</template>

<script>
  const columns = [
    { title: 'Index', dataIndex: 'index', key: 'index' },
    { title: 'Status', dataIndex: 'status', key: 'status'},
    { title: 'Product Number', dataIndex: 'product_number', key: 'product_number' },
    { title: 'Cost', dataIndex: 'cost', key: 'cost' },
    { title: 'Created Time', dataIndex: 'created_time', key: 'created_time' },
    { title: 'Operation', dataIndex: 'operation', key: 'operation', scopedSlots: { customRender: 'operation' }}
  ];

  // const data = [];
  // for (let i = 0; i < 3; ++i) {
  //   data.push({
  //     key: i,
  //     index: i + 1,
  //     status: 'iOS',
  //     product_number: '10.3.4.5654',
  //     cost: 500,
  //     created_time: '2014-12-24 23:12:00',
  //   });
  // }

  const innerColumns = [
    { title: 'Date', dataIndex: 'date', key: 'date' },
    { title: 'Name', dataIndex: 'name', key: 'name' },
    { title: 'Status', key: 'state', scopedSlots: { customRender: 'status' } },
    { title: 'Upgrade Status', dataIndex: 'upgradeNum', key: 'upgradeNum' }
  ];

  const innerData = [];
  for (let i = 0; i < 3; ++i) {
    innerData.push({
      key: i,
      date: '2014-12-24 23:12:00',
      name: 'This is production name',
      upgradeNum: 'Upgraded: 56',
    });
  }

  import { Table, Badge, Button } from 'ant-design-vue'
  import { cancelOrder } from "network/order";

  export default {
    name: "OrderList",
    components: {
      'a-table': Table,
      'a-badge': Badge,
      'a-button': Button
    },
    props: {
      orders: Array
      // innerData: []
    },
    data() {
      return {
        data: [],
        columns,
        innerColumns,
        innerData
      }
    },
    watch: {
      orders: function (newOrders) {
        // console.log(newOrders);
        this.pushData()
      }
    },
    methods: {
      cancelOrder(orderId, index) {
        console.log(orderId);
        cancelOrder(orderId).then(res => {
          if(res.error == '') {
            console.log(res);
            this.orders[index].type = '已取消订单'
            this.orders[index].status = 2
            this.data[index].status = '已取消订单'
            this.data[index].operation.status = 2
          }
        })
      },
      pushData() {
        this.data = []
        for(let i = 0; i < this.orders.length; i++) {
          this.data.push({
            key: i,
            index: i + 1,
            status: this.orders[i].type,
            product_number: this.orders[i].products.length,
            cost: this.orders[i].cost.toFixed(2),
            created_time: this.orders[i].created_time,
            operation: {
              status: this.orders[i].status,
              orderId: this.orders[i].id,
              index: i
            }
          });
        }
      }
    },
    mounted() {
      this.pushData()
      // console.log(this.data);
    }
  }
</script>

<style scoped>

</style>
