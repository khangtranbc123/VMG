<template>
  <div id="app" class="columns">
    <div class="column">
      <button class="is-primary button" @click="shuffle">
        Shuffle!
      </button>
      <Tweet v-for="tweet in tweets" :key="tweet.id" :tweet="tweet"/>
    </div>
  </div>
</template>

<script>
//Data
const tweets = [
  {
    id: 1,
    name: 'James',
    handle: '@jokerjames',
    img: 'https://semantic-ui.com/images/avatar2/large/matthew.png',
    tweet: "If you don't succeed, dust yourself off and try again.",
    likes: 10,
  },
  {
    id: 2,
    name: 'Fatima',
    handle: '@fantasticfatima',
    img: 'https://semantic-ui.com/images/avatar2/large/molly.png',
    tweet: 'Better late than never but never late is better.',
    likes: 12,
  },
  {
    id: 3,
    name: 'Xin',
    handle: '@xeroxin',
    img: 'https://semantic-ui.com/images/avatar2/large/elyse.png',
    tweet: 'Beauty in the struggle, ugliness in the success.',
    likes: 18,
  }
]
//
import Tweet from "@/components/Tweet";
let shuffle = require('lodash.shuffle');
export default {
  name: 'App',
  components: {
    Tweet
  },
  data() {
    return {
      tweets: tweets
    }
  },
  methods: {
    shuffle() {
      this.tweets = shuffle(this.tweets)
    }
  }
}
</script>

<style>
html, body {
  height: 100%;
  padding-top: 10px;
  background: #e6ecf1;
}
#app {
  height: 100%;
  padding-top: 0px;
  text-align: center;
}
#app .button {
  margin-bottom: 20px;
}
#app .tweet {
  max-width: 500px;
  margin: 0 auto;
  padding-bottom: 15px;
}
#app .box {
  margin-bottom: 0;
  border-radius: 0;
}
#app .content small {
  color: #00d1b2;
}
#app img {
  border-radius: 30px;
}
#app .level-item {
  padding-left: 10px;
  color: #00d1b2;
}
#app input:focus {
  border-color: #00d1b2;
}
#app .likes {
  padding: 0 7.5px;
}
#app input {
  font-weight: bold;
}
#app .tweets-move {
  transition: transform 1s;
}
</style>
