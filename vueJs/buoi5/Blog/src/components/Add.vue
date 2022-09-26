<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="blog.title"
          name="title"
        />
      </div>
      <div class="form-group">
        <label for="content">content</label>
        <input
          type="text"
          class="form-control"
          id="content"
          required
          v-model="blog.content"
          name="content"
        />
      </div>
      <button @click="saveTutorial" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newTutorial">Add</button>
    </div>
  </div>
</template>

<script>
import BlogService from '../service/BlogService'
export default {
  name: 'add-blog',
  data () {
    return {
      blog: {
        id: null,
        title: '',
        content: '',
        published: false
      },
      submitted: false
    }
  },
  methods: {
    saveTutorial () {
      var data = {
        title: this.blog.title,
        content: this.blog.content
      }

      BlogService.create(data)
        .then(response => {
          this.blog.id = response.data.id
          console.log(response.data)
          this.submitted = true
        })
        .catch(e => {
          console.log(e)
        })
    },
    newTutorial () {
      this.submitted = false
      this.tutorial = {}
    }
  }
}
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
