<template>
    <div class="text">
        {{text}}
    </div>
</template>
<style>
    .text {
        color:red;
        border: 1px solid black;
        padding: 10px;
    }
</style>
<script>
    export default {
        name: "TestElement",
        props: {
            text: "x",
        },
        data: function() {
            return {
            }
        },
        ready: function() {
            console.log("### " + this.text + " ready!");
        },
    }
</script>