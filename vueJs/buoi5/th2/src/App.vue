<template>
  <center id="app">
    <h1 style="color: green">GeeksforGeeks</h1>
    <h2><u>To Do List</u></h2>
    <Task task="Run a mile" index="0" />
    <Task task="Code on GFG" index="1" />
    <Task task="Clean Room" index="2" />
    <Task task="Go to Swim" index="3" />
    <Task task="Buy Dog Food" index="4" />
  </center>
</template>

<script>
  import Task from "./components/Task";
  export default {
    name: "App",
    components: { Task },
  };
</script>
