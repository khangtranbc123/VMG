<template>
  <h1 v-for="product in products" :key="product">{{ product }}</h1>
  <hr />
  <div v-for="(product, index) in productDetails" :key="index">
    <h4>{{index+1}}. Name: {{ product.name }} | Price: {{ product.price }}</h4>
    <hr />
  </div>
  <div v-for="(product, index) in productCatDetails" :key="index">
    <h3>{{index + 1}}. {{product.category}}</h3>
    <ul>
      <li  v-for="prod in product.products" :key="prod">{{prod}}</li>
    </ul>
  </div>
  <hr>
  <div v-for="(value, key, index) in iphoneSpecs" :key="key">
    <h4>{{index + 1}}. {{key}} - {{value}}</h4>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      products: ["Mac studio", "Ipad air", "Iphone SE", "Iphone 13 pro"],
      productDetails: [
        {
          name: "Mac studio",
          price: "7999$",
        },
        {
          name: "Ipad air",
          price: "699$",
        },
        {
          name: "Iphone SE",
          price: "429$",
        },
        {
          name: "Iphone 13 pro",
          price: "999$",
        },
      ],
      productCatDetails: [
        {
          category: "Iphone",
          products: ["Iphone 10", "Iphone 11", "Iphone 12", "Iphone 13"],
        },
        {
          category: "Ipad",
          products: ["Ipad", "Ipad mini", "Ipad air", "Ipad pro"],
        },
        {
          category: "Mac",
          products: [
            "Imac",
            "Imac pro",
            "Macbook air",
            "Macbook pro",
            "Mac studio",
          ],
        },
      ],
      iphoneSpecs: {
        processor: "A15 bionic",
        camera: "16MP/8MP REAR and 8MP FRONT",
        color: "Midnight black",
        memory: "256 GB",
      },
    };
  },
};
</script>
