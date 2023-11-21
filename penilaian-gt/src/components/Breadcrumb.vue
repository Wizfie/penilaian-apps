<template>
  <nav style="--bs-breadcrumb-divider: '-'">
    <ol class="breadcrumb fs-5">
      <li class="breadcrumb-item">
        <router-link
          :class="{ active: $route.path === '/dashboard' }"
          to="/dashboard"
          ><a>Home</a></router-link
        >
      </li>
      <li class="breadcrumb-item">
        <router-link :class="{ active: $route.path === '/plan' }" to="/plan"
          ><a>Plan</a></router-link
        >
      </li>
      <li class="breadcrumb-item">
        <router-link :class="{ active: $route.path === '/do' }" to="/do"
          ><a>Do</a></router-link
        >
      </li>
      <li class="breadcrumb-item">
        <router-link :class="{ active: $route.path === '/check' }" to="/check"
          ><a>Check</a></router-link
        >
      </li>
      <li class="breadcrumb-item">
        <router-link :class="{ active: $route.path === '/action' }" to="/action"
          ><a>Action</a></router-link
        >
      </li>
      <li class="breadcrumb-item">
        <router-link :class="{ active: $route.path === '/action' }" to="/action"
          ><a>Action</a></router-link
        >
      </li>
    </ol>
  </nav>
</template>
<script>
export default {};
</script>
<style lang=""></style>
