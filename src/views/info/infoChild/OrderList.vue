<template>
  <div id="order-list">
    <a-table :columns="columns" :data-source="data" class="components-table-demo-nested">
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
    { title: 'Status', dataIndex: 'status', key: 'status' },
    { title: 'Product Number', dataIndex: 'product_number', key: 'product_number' },
    { title: 'Cost', dataIndex: 'cost', key: 'cost' },
    { title: 'Created Time', dataIndex: 'created_time', key: 'created_time' },
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

  import { Table, Badge } from 'ant-design-vue'

  export default {
    name: "OrderList",
    components: {
      'a-table': Table,
      'a-badge': Badge
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
    mounted() {
      for(let i = 0; i < this.orders.length; i++) {
        this.data.push({
          key: i,
          index: i + 1,
          status: this.orders[i].type,
          product_number: this.orders[i].products.length,
          cost: this.orders[i].cost.toFixed(2),
          created_time: this.orders[i].created_time,
        });
      }
    }
  }
</script>

<style scoped>

</style>
