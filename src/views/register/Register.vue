<template>
  <div id="register">
    <a-form :form="form" @submit="handleSubmit">
      <a-form-item>
        <div style="width: 100%; margin-left: 60%; margin-top: 100px">
          <h2>Register</h2>
        </div>
      </a-form-item>
      <a-form-item v-bind="formItemLayout">
        <span slot="label">
          Username&nbsp;
          <a-tooltip title="What do you want others to see you on the internet?">
            <a-icon type="question-circle-o" />
          </a-tooltip>
        </span>
        <a-input
            v-decorator="[
            'nickname',
            {
              rules: [{ required: true, message: 'Please input your username!', whitespace: true }],
            },
          ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Password" has-feedback>
        <a-input
            v-decorator="[
            'password',
            {
              rules: [
                {
                  required: true,
                  message: 'Please input your password!',
                },
                {
                  validator: validateToNextPassword,
                },
              ],
            },
          ]"
            type="password"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Confirm Password" has-feedback>
        <a-input
            v-decorator="[
            'confirm',
            {
              rules: [
                {
                  required: true,
                  message: 'Please confirm your password!',
                },
                {
                  validator: compareToFirstPassword,
                },
              ],
            },
          ]"
            type="password"
            @blur="handleConfirmBlur"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Phone Number">
        <a-input
            v-decorator="[
                'phonenumber',
                {
                  rules: []
                }
            ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="E-mail">
        <a-input
            v-decorator="[
            'email',
            {
              rules: [
                {
                  type: 'email',
                  message: 'The input is not valid E-mail!',
                },
                {
                  required: false,
                  message: 'Please input your E-mail!',
                },
              ],
            },
          ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Habitual Residence">
        <a-cascader
            v-decorator="[
            'residence',
            {
              initialValue: ['zhejiang', 'hangzhou', 'xihu'],
              rules: [
                { type: 'array', required: false, message: 'Please select your habitual residence!' },
              ],
            },
          ]"
            :options="residences"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="birthday">
        <a-date-picker
            v-decorator="[
                'birthday',
                {
                  rules: [
                      { required: false, message: 'Please select your birthday!' }
                  ],
                  initialValue: moment('2000-01-01', 'YYYY-MM-DD')
                }
            ]"
            format="YYYY-MM-DD"
            :disabled-date="disabledDate"
            style="width: 100%"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="gender">
        <a-select
          v-decorator="[
              'gender',
              {
                initialValue: 'male'
              }
          ]"
          style="width: 100%"
        >
          <a-select-option value="male">
            male
          </a-select-option>
          <a-select-option value="female">
            female
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="status">
        <a-select
            v-decorator="[
              'status',
              {
                initialValue: 'user'
              }
          ]"
            style="width: 100%"
        >
          <a-select-option value="user">
            user
          </a-select-option>
          <a-select-option value="shopper">
            shopper
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-bind="tailFormItemLayout">
        <a-checkbox v-decorator="[
           'agreement',
          {
            valuePropName: 'checked',
            rules: [ { required: true } ]
          }]">
          I have read the
          <a href="">
            agreement
          </a>
        </a-checkbox>
      </a-form-item>
      <a-form-item v-bind="tailFormItemLayout">
        <a-button type="primary" html-type="submit">
          Register
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import moment from 'moment'
import { SETLOGIN } from "@/store/mutations-types";
import { residences } from "./Residence";
import { addUser } from "network/user";
import { Form, Input, Tooltip, Icon, Cascader,
          Select, AutoComplete, Row, Col, Button,
          Checkbox, DatePicker } from 'ant-design-vue'

export default {
  name: "Register",
  components: {
    'a-form': Form,
    'a-form-item': Form.Item,
    'a-input': Input,
    'a-tooltip': Tooltip,
    'a-icon': Icon,
    'a-cascader': Cascader,
    'a-select': Select,
    'a-select-option': Select.Option,
    'a-auto-complete': AutoComplete,
    'a-row': Row,
    'a-col': Col,
    'a-button': Button,
    'a-checkbox': Checkbox,
    'a-date-picker': DatePicker,
    'a-month-picker': DatePicker.MonthPicker,
    'a-range-picker': DatePicker.RangePicker
  },
  data() {
    return {
      form: this.$form.createForm(this, { name: 'register' }),
      confirmDirty: false,
      residences,
      autoCompleteResult: [],
      formItemLayout: {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
      },
      tailFormItemLayout: {
        wrapperCol: {
          xs: {
            span: 24,
            offset: 0,
          },
          sm: {
            span: 16,
            offset: 8,
          },
        },
      },
    };
  },
  // beforeCreate() {
  //   this.form = this.$form.createForm(this, { name: 'register' });
  // },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
          let address = ''
          for(let i = 0; i < values.residence.length; i++) {
            address += values.residence[i]
          }
          let ageString = values.birthday == undefined ? '0' : values.birthday.fromNow()
          let age = parseInt(ageString.split(' ')[0])
          console.log(age);
          console.log(values.birthday.format('MM-DD'));
          addUser(values.nickname,
              values.password,
              address,
              values.phonenumber == undefined ? null : values.phonenumber,
              values.gender,
              age,
              values.birthday.format('MM-DD'),
              values.email == undefined ? null : values.email,
              values.status == 'user' ? 1 : 2,
              0,
              0
          ).then(res => {
            console.log(res);
            if(res.error == '') {
              alert('register successfully!')
              const payload = {
                userId: values.nickname,
                isLogin: true
              }
              this.$store.commit(SETLOGIN, payload)
              this.$router.replace('/home')
            }
          })
        }
      });
    },
    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },
    compareToFirstPassword(rule, value, callback) {
      const form = this.form;
      if (value && value !== form.getFieldValue('password')) {
        callback('Two passwords that you enter is inconsistent!');
      } else {
        callback();
      }
    },
    validateToNextPassword(rule, value, callback) {
      const form = this.form;
      if (value && this.confirmDirty) {
        form.validateFields(['confirm'], { force: true });
      }
      callback();
    },
    moment,
    range(start, end) {
      const result = [];
      for (let i = start; i < end; i++) {
        result.push(i);
      }
      return result;
    },
    disabledDate(current) {
      // Can not select days after today and today
      return current && current > moment().endOf('day');
    }
  },
  mounted() {
    const payload = {
      userId: '',
      isLogin: false
    }
    this.$store.commit(SETLOGIN, payload)
  }
};
</script>

<style scoped>
  #register {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-60%,-50%);
    width: 40%;
  }
</style>
