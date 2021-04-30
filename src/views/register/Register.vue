<template>
  <div id="register">
    <a-form :form="form" @submit="handleSubmit">
      <a-form-item>
        <div style="width: 100%; margin-left: 60%">
          <h2>Register</h2>
        </div>
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
                  required: true,
                  message: 'Please input your E-mail!',
                },
              ],
            },
          ]"
        />
      </a-form-item>
      <a-form-item>

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
      <a-form-item v-bind="formItemLayout">
        <span slot="label">
          Nickname&nbsp;
          <a-tooltip title="What do you want others to call you?">
            <a-icon type="question-circle-o" />
          </a-tooltip>
        </span>
        <a-input
            v-decorator="[
            'nickname',
            {
              rules: [{ required: true, message: 'Please input your nickname!', whitespace: true }],
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
                { type: 'array', required: true, message: 'Please select your habitual residence!' },
              ],
            },
          ]"
            :options="residences"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Website">
        <a-auto-complete
            v-decorator="['website', { rules: [{ required: true, message: 'Please input website!' }] }]"
            placeholder="website"
            @change="handleWebsiteChange"
        >
          <template slot="dataSource">
            <a-select-option v-for="website in autoCompleteResult" :key="website">
              {{ website }}
            </a-select-option>
          </template>
          <a-input />
        </a-auto-complete>
      </a-form-item>
      <a-form-item
          v-bind="formItemLayout"
          label="Captcha"
          extra="We must make sure that your are a human."
      >
        <a-row :gutter="8">
          <a-col :span="12">
            <a-input
                v-decorator="[
                'captcha',
                { rules: [{ required: true, message: 'Please input the captcha you got!' }] },
              ]"
            />
          </a-col>
          <a-col :span="12">
            <a-button>Get captcha</a-button>
          </a-col>
        </a-row>
      </a-form-item>
      <a-form-item v-bind="tailFormItemLayout">
        <a-checkbox v-decorator="['agreement', { valuePropName: 'checked' }]">
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
const residences = [
  {
    value: 'zhejiang',
    label: 'Zhejiang',
    children: [
      {
        value: 'hangzhou',
        label: 'Hangzhou',
        children: [
          {
            value: 'xihu',
            label: 'West Lake',
          },
        ],
      },
    ],
  },
  {
    value: 'jiangsu',
    label: 'Jiangsu',
    children: [
      {
        value: 'nanjing',
        label: 'Nanjing',
        children: [
          {
            value: 'zhonghuamen',
            label: 'Zhong Hua Men',
          },
        ],
      },
    ],
  },
];

import { Form, Input, Tooltip, Icon, Cascader, Select, AutoComplete, Row, Col, Button, Checkbox } from 'ant-design-vue'

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
    'a-auto-complete': AutoComplete,
    'a-row': Row,
    'a-col': Col,
    'a-button': Button,
    'a-checkbox': Checkbox
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
    handleWebsiteChange(value) {
      let autoCompleteResult;
      if (!value) {
        autoCompleteResult = [];
      } else {
        autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
      }
      this.autoCompleteResult = autoCompleteResult;
    },
  },
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
