<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
html, body{
  width:100%;
  height: 100%;
  overflow: hidden;
}
*, :after, :before {
  box-sizing: border-box;
}
#app {
  width:100%;
  height: 100%;
  margin:0;
  padding:0;
      overflow-y: auto;
}
.vertical-center-modal,.ivu-modal-wrap{
    display: flex;
    align-items: center;
    justify-content: center;
}
.ivu-modal{
    top: 0 !important;
}
</style>
