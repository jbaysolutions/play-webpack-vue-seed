<script>
    new Vue({
        el: '#websocketsContainer',
        data: {
            items: [1, 10, 20, 30]
        },
        ready: function () {
            this.socket = io("http://localhost:9000/ws");
            this.socket.on('connect', function(msg) {
                console.log("### " + msg);
            })
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