let myChart = undefined;

const datasetsAll = [{
  backgroundColor: 'rgba(54, 162, 235, 0.2)',
  borderColor: 'rgba(54, 162, 235, 1)',
  borderWidth: 1
}, {
  backgroundColor: 'rgba(255, 159, 64, 0.2)',
  borderColor: 'rgba(255, 159, 64, 1)',
  borderWidth: 1
}, {
  backgroundColor: 'rgba(75, 192, 192, 0.2)',
  borderColor: 'rgba(75, 192, 192, 1)',
  borderWidth: 1
}, {
  backgroundColor: 'rgba(153, 102, 255, 0.2)',
  borderColor: 'rgba(153, 102, 255, 1)',
  borderWidth: 1
}];

const datasetsDefault = [{
  backgroundColor: 'rgba(54, 162, 235, 0.2)',
  borderColor: 'rgba(54, 162, 235, 1)',
  borderWidth: 1
}];

const statsDocs = () => {
  return {
    formMessage: '',
    async loadStats(filtre) {
      const data = await this.getStats(filtre);
      if (filtre == 'date' || filtre == 'type') {
        myChart.options.plugins.legend = false;
        while (myChart.data.datasets.length > 1) {
          myChart.data.datasets.pop();
        }
        myChart.data.datasets = datasetsDefault;
        myChart.data.datasets[0].data = data.map(e => e.value);
        myChart.data.labels = data.map(e => filtre == 'date' ? e.dateArchivage : e.type);
      } else if (filtre == 'all') {
        console.log(data);
        myChart.data.labels = [...new Set(data.map(e => new Date(e.dateArchivage).toLocaleDateString("fr")))];
        [...new Set(data.map(e => e.type))].forEach((type, i) => {
          myChart.data.datasets[i] = datasetsAll[i] || {};
          myChart.data.datasets[i].data = data.filter(e => e.type == type).map(e => e.value);
          myChart.data.datasets[i].label = type.charAt(0).toUpperCase() + type.slice(1);
        });
        myChart.options.plugins.legend = true;
      }
      myChart.update();
    },
    async initChart() {
      const ctx = document.getElementById('myChart');
      myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          datasets: datasetsDefault
        },
        options: {
          plugins: {
            legend: {
              display: false
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              ticks: {
                precision: 0
              }
            }
          }
        }
      });
      await this.loadStats('date');
    },
    async getStats(filtre) {
      this.formMessage = '';
      return fetchApi(
        '/Statistiques/' + filtre,
        'GET',
        this.formData,
        async (response) => {
          this.formMessage = 'Statistiques chargés';
          return response.json();
        },
        (e) => {
          this.formMessage = 'Qqch n\'a pas fonctionné ...';
        }
      );
    }
  };
}



