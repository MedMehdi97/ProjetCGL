let myChart = undefined;

const datasetsAll = [{
  backgroundColor: 'rgba(225, 225, 225, 0.2)',
  borderColor: 'rgba(225, 225, 225, 1)',
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
  backgroundColor: 'rgba(225, 225, 225, 0.2)',
  borderColor: 'rgba(225, 225, 225, 1)',
  borderWidth: 1
}];

const statsDocs = () => {
  return {
    formMessage: '',
    async loadStats(filtre) {
      const data = await this.getStats(filtre);
      if (filtre == 'date' || filtre == 'type') {
        myChart.options.plugins.legend.display = false;
        while (myChart.data.datasets.length > 1) {
          myChart.data.datasets.pop();
        }
        myChart.data.datasets = datasetsDefault;
        myChart.data.datasets[0].data = data.map(e => e.value);
        myChart.data.labels = data.map(e => filtre == 'date' ? e.dateArchivage : e.type);
      } else if (filtre == 'all') {
        myChart.data.labels = [...new Set(data.map(e => new Date(e.dateArchivage).toLocaleDateString("fr")))];
        [...new Set(data.map(e => e.type))].forEach((type, i) => {
          myChart.data.datasets[i] = datasetsAll[i] || {};
          myChart.data.datasets[i].data = data.filter(e => e.type == type).map(e => e.value);
          myChart.data.datasets[i].label = type.charAt(0).toUpperCase() + type.slice(1);
        });
        myChart.options.plugins.legend.display = true;
      }
      myChart.update();
    },
    async initChart() {
      Chart.defaults.font.size = 20;
      Chart.defaults.color = '#fff';
      const ctx = document.getElementById('myChart');
      ctx.style.backgroundColor = 'rgba(0,0,0,0.3)';
      myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          datasets: datasetsDefault
        },
        plugins: [ChartDataLabels],
        options: {
          plugins: {
            datalabels: {
              anchor: 'middle',
              color: 'rgba(225,225,225,0.65)',
              font: function (context) {
                var width = context.chart.width;
                var size = Math.round(width / 65);
                return {
                  size: size,
                  weight: 600
                };
              },
            }
          },
          scales: {
            x: {
              grid: {
                color: 'rgba(250,250,250,0.15)'
              }
            },
            y: {
              beginAtZero: true,
              ticks: {
                precision: 0,
              },
              grid: {
                color: 'rgba(250,250,250,0.3)'
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



