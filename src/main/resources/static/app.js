const app = Vue.createApp({
  data() {
    return {
      pitMap: {
              "1": 0,
              "2": 0,
              "3": 0,
              "4": 0,
              "5": 0,
              "6": 0,
              "7": 0,
              "8": 0,
              "9": 0,
              "10": 0,
              "11": 0,
              "12": 0,
              "13": 0,
              "14": 0
          },
      nextPlayer : 'NONE',
      winningPlayer : 'NONE'
    };
  },
  methods: {
    startGame(){
        var vm = this;
        axios
              .get('http://localhost:8080/mancala/game/init')
              .then(function (response) {
                  // handle success
                    vm.pitMap = response.data.pitMap;
                    vm.nextPlayer = response.data.nextPlayer;
                    vm.winningPlayer = response.data.winningPlayer;
                  console.log('Game Started');
                })
                .catch(function (error) {
                  // handle error
                  console.log(error);
                });
    },
    pickPit(player, pit){
        var vm = this;
        axios
              .put('http://localhost:8080/mancala/game/'+player+'/'+pit)
              .then(function (response) {
                  // handle success
                  vm.pitMap = response.data.pitMap;
                  vm.nextPlayer = response.data.nextPlayer;
                  vm.winningPlayer = response.data.winningPlayer;
                  console.log(vm.pitMap);
                  console.log(vm.nextPlayer);
                  console.log(vm.winningPlayer);
                })
                .catch(function (error) {
                  // handle error
                  console.log(error);
                });
    }
  },
});

app.mount('.board');