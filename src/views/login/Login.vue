<template>
  <div id="login">
    <a-form
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit="handleSubmit"
    >
      <a-form-item>
        <div style="width: 100%; text-align: center">
          <h2>Login</h2>
        </div>
      </a-form-item>
      <a-form-item>
        <a-input
            v-decorator="[
              'userName',
              { rules: [{ required: true, message: 'Please input your username!' }] },
            ]"
            placeholder="Username/Email"
        >
          <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
            v-decorator="[
              'password',
              { rules: [{ required: true, message: 'Please input your Password!' }] },
            ]"
            type="password"
            placeholder="Password"
        >
          <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-checkbox
            v-decorator="[
          'remember',
          {
            valuePropName: 'checked',
            initialValue: true,
          },
        ]"
        >
          Remember me
        </a-checkbox>
        <a class="login-form-forgot" href="">
          Forgot password
        </a>
        <a-button type="primary" html-type="submit" class="login-form-button">
          Log in
        </a-button>
        Or
        <a @click="registerClick">
          register now!
        </a>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
  import { Form, Icon, Checkbox, Input, Button } from 'ant-design-vue'

  export default {
    name: "Login",
    data() {
      return {
        form: this.$form.createForm(this, { name: 'normal_login' })
      }
    },
    components: {
      'a-form': Form,
      'a-form-item': Form.Item,
      'a-icon': Icon,
      'a-checkbox': Checkbox,
      'a-input': Input,
      'a-button': Button
    },
    // beforeCreate() {
    //   this.form = this.$form.createForm(this, { name: 'normal_login' })
    // },
    methods: {
      handleSubmit(e) {
        e.preventDefault()
        this.form.validateFields((err, values) => {
          if (!err) {
            console.log('Received values of form: ', values)
          }
        })
      },
      registerClick() {
        this.$router.replace('/register')
      }
    }
  }
</script>

<style scoped>
  #login {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }

  #components-form-demo-normal-login .login-form {
    max-width: 300px;
  }

  #components-form-demo-normal-login .login-form-forgot {
    float: right;
  }

  #components-form-demo-normal-login .login-form-button {
    width: 100%;
  }
</style>
