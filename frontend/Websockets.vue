<script>
    new Vue({
        el: '#websocketsContainer',
        data: {
            items: [1, 10, 20, 30]
        },
        ready: function () {
            console.log("### WebSockets READY!");
        },
        watch: {
        },
        methods: {
            addItem: function() {
                this.items.push(Math.random());
            }
        },
    });
</script>