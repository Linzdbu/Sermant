<template>
  <div style="width: 100%">
    <el-page-header :title="'事件'" @back="goBack">
      <template #content>
        <span class="font-600 mr-3"> 事件配置 </span>
      </template>
    </el-page-header>
    <h2>WebHook 配置</h2>
    <el-table :data="webhooks" :table-layout="'auto'">
      <el-table-column prop="address" label="开关" width="80">
        <template #default="scope">
          <el-switch v-model="scope.row.enable" @change="setWebHook(scope.row)" />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="类型" />
      <el-table-column prop="url" label="地址">
        <template #default="scope">
          <el-input
            @change="setWebHook(scope.row)"
            @blur="closeEditWebHook(scope.row)"
            v-model="scope.row.url"
            :disabled="!scope.row.canEdit"
          />
        </template>
      </el-table-column>
      <el-table-column prop="address" label="操作">
        <template #default="scope">
          <el-button size="large" @click="editWebHook(scope.row)">编辑</el-button>
          <el-button size="large" @click="testWebHook(scope.row)">测试连接</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {onBeforeMount, reactive, ref} from "vue";
import {useRouter} from "vue-router";
import axios from "axios";

const router = useRouter();

const count = ref(0);

const value1 = ref(true);

const webhooks = reactive([
  {
    id: 0,
    name: "飞书",
    url: "",
    enable: false,
    canEdit: false,
  },
  {
    id: 1,
    name: "钉钉",
    url: "",
    enable: false,
    canEdit: false,
  },
  // 暂不支持welink
  // {
  //   id: 2,
  //   name: "welink",
  //   url: "",
  //   enable: false,
  //   canEdit: false,
  // },
]);

const goBack = () => {
  router.push("/events");
};

onBeforeMount(() => {
  getWebHooks();
});

const getWebHooks = () => {
  axios
    .get(`${window.location.origin}/sermant/event/webhooks`)
    .then(function (response) {
      ElMessage({
        message: "获取WebHook配置成功",
        type: "success",
      });
      const data = response.data;
      for (let index = 0; index < data.webhooks.length; index++) {
        webhooks[data.webhooks[index].id].url = data.webhooks[index].url;
        webhooks[data.webhooks[index].id].enable = data.webhooks[index].enable;
      }
    })
    .catch(function (error) {
      ElMessage({
        message: "获取WebHook配置失败",
        type: "error",
      });
      console.log(error);
    });
};

const editWebHook = (webhook) => {
  webhook.canEdit = true;
};

const closeEditWebHook = (webhook) => {
  webhook.canEdit = false;
};

const setWebHook = (webhook) => {
  console.log(webhook);
  webhook.canEdit = false;
  axios
    .put(`${window.location.origin}/sermant/event/webhooks/` + webhook.id, {
      url: webhook.url,
      enable: webhook.enable,
    })
    .then(function (response) {
      const data = response.data;
      if (data) {
        ElMessage({
          message: "设置成功",
          type: "success",
        });
      } else {
        ElMessage({
          message: "设置失败",
          type: "error",
        });
      }
    })
    .catch(function (error) {
      ElMessage({
        message: "设置失败",
        type: "error",
      });
      console.log(error);
    });
};

const testWebHook = (webhook) => {
  axios
    .post(`${window.location.origin}/sermant/event/webhooks/test`, {
      id: webhook.id,
    })
    .then(function (response) {
      ElMessage({
        message: "发起WebHook测试成功",
        type: "success",
      });
    })
    .catch(function (error) {
      ElMessage({
        message: "发起WebHook测试失败",
        type: "error",
      });
      console.log(error);
    });
};
</script>
