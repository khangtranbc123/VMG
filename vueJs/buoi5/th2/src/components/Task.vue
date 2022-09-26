<template>
	<h3 style=
	"color: white;
	backgroundColor: green;
	width: 300px;
	height: 30px;
	padding: 15px;">
	{{index}} - {{task}}
	</h3>
</template>

<script>
export default {
	name: 'Task',
	props : ['task','index']
}
</script>
