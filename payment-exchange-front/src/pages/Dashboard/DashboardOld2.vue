<template>
  <v-container fluid>
    <div class="dashboard-page">
      <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
        <h1 class="page-title">Dashboard</h1>
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              color="secondary"
              class="text-capitalize button-shadow mr-1"
            >Latest Reports
            </v-btn
            >
          </template>
        </v-menu>
      </v-row>

      <v-col cols="12">
        <v-card class="mx-1 mb-1">
          <v-card-title class="pa-6 pb-0">
            <v-row no-gutters>
              <v-col
                cols="7"
                sm="4"
                md="4"
                lg="5"
                class="d-flex align-center"
              >
                <p>Earned provision</p>
              </v-col>
              <v-col cols="5" sm="4" md="4" lg="4" offset-lg="1">
                <v-menu>
                  <template v-slot:activator="{ on, attrs }">
                    <v-select
                      class="main-chart-select"
                      v-model="mainApexAreaSelect"
                      v-bind="attrs"
                      v-on="on"
                      dense
                      flat
                      single-line
                      hide-details
                      :items="mock.select"
                      outlined
                    ></v-select>
                  </template>
                </v-menu>
              </v-col>
            </v-row>
          </v-card-title>
          <v-card-text class="pa-6">
            <v-row>
              <v-col>
                <ApexChart
                  v-if="!apexLoading"
                  height="450"
                  type="area"
                  :options="this.mainApexArea.options"
                  :series="
                      mainApexAreaSelect === 'Daily'
                        ? this.mainApexArea.series
                        : mainApexAreaSelect === 'Weekly'
                        ? this.mainApexArea.series2
                        : this.mainApexArea.series3
                    "
                ></ApexChart>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col lg="4" sm="6" cols="12">
        <v-card class="support-requests mx-1 mb-1">
          <v-card-title class="pa-6 pb-0">
            <h2 class="page-title">Today provision</h2>
          </v-card-title>
          <v-card-text class="pa-0">
            <v-simple-table>
              <template v-slot:default>
                <tbody>
                <tr>
                  <td>BUY</td>
                  <td>{{Number(currentCommission.buyCommission).toFixed(2)}} RSD</td>
                </tr>
                <tr>
                  <td>SELL</td>
                  <td>{{Number(currentCommission.sellCommission).toFixed(2)}} RSD</td>
                </tr>
                <tr>
                  <td>TOTAL</td>
                  <td>{{Number(currentCommission.buyCommission + currentCommission.sellCommission).toFixed(2)}} RSD</td>
                </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-card-text>
        </v-card>
      </v-col>

    </div>
  </v-container>
</template>

<script>
import mock from "./mock";
import Trend from "vuetrend";
import ApexChart from "vue-apexcharts";
import config from "@/config";

export default {
  name: "Dashboard",
  components: {
    Trend,
    ApexChart,
  },
  data() {
    return {
      mainApexArea: {
        options: {
          chart: {
            width: '100%',
            toolbar: {
              show: false
            },
          },
          dataLabels: {
            enabled: true
          },
          grid: {
            xaxis: {
              lines: {
                show: true,
              }
            },
            yaxis: {
              lines: {
                show: true,
              },
            }
          },
          markers: {
            size: [0, 0, 0]
          },
          stroke: {
            width: [3, 3, 0],
            curve: ['straight', 'straight', "straight"]
          },
          fill: {
            type: 'linear',
          },
          legend: {
            show: true

          },
          colors: [config.light.warning, config.light.primary, '#f3f5ff'],
          yaxis: {
            axisBorder: {
              show: true,
              color: '#B9B9B980'
            },
            labels: {
              style: {
                colors: ['#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980'],
                fontSize: '12px',
              },
            },
            tickAmount: 5
          },
          xaxis: {
            //imena colona
            categories: [],
            labels: {
              style: {
                fontSize: '12px',
                colors: ['#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980', '#B9B9B980'],
              }
            },
            tickAmount: 10,
          }
        },
        series: [
          {
            name: 'Buy Commission',
            type: 'line',
            data: []
          },
          {
            name: 'Sell commission',
            type: 'line',
            data: []
          }
        ],
        series2: [
          {
            name: 'Tablet',
            type: 'line',
            data: [2100, 3000, 2800, 6100, 5200, 5090, 4100, 2100, 2000, 3800, 6100, 5200, 7090, 6100]
          },
          {
            name: 'Mobile',
            type: 'line',
            data: [1100, 3200, 4500, 3020, 3400, 5200, 4100, 1100, 3200, 4500, 3020, 3400, 5200, 4100]
          },
          {
            name: 'Desktop',
            type: 'area',
            data: [2100, 2000, 1800, 4100, 1200, 3600, 6100, 2100, 2000, 1800, 4100, 1200, 3600, 6100]
          }
        ],
        series3: [
          {
            name: 'Tablet',
            type: 'line',
            data: [4100, 5000, 4800, 8100, 7200, 7090, 6100, 4100, 4000, 5800, 8100, 7200, 9090, 8100]
          },
          {
            name: 'Mobile',
            type: 'line',
            data: [4100, 5200, 7500, 6020, 6400, 8200, 7100, 4100, 6200, 7500, 6020, 6400, 8200, 7100]
          },
          {
            name: 'Desktop',
            type: 'area',
            data: [5100, 5000, 4800, 7100, 4200, 6600, 9100, 5100, 5000, 4800, 7100, 4200, 4600, 9100]
          }
        ],
      },

      weekCommissionRecords: [{
        buyCommission: null,
        sellCommission: null,
        totalCommission: null,
        userId: null,
        dateOfCommission: null
      }],
      workPlace: {
        workPlaceId: null,
        cashRegisterId: null,
        userId: null,
        timestampOfOpen: null,
        timestampOfClose: null,
        timestampOfCreation: null,
      },
      mainApexSellCommission: [],
      workPlaceStatus: null,
      mock,
      apexLoading: true,
      value: this.getRandomInt(10, 90),
      value2: this.getRandomInt(10, 90),
      mainApexAreaSelect: "Daily",
      currencyList: [{
        currencyCode: null,
        currencyNameCode: null,
        fullName: null,
        quantity: null,
        country: null,
        signLogo: null,
      }],
      cashRegisterBalance: {
        cashRegisterId: null,
        cashRegisterName: null,
        balanceList: [{
          currencyCode: null,
          balance: null
        }]
      },
      currentCommission: {
        buyCommission: 0,
        sellCommission: 0,
        totalCommission: 0,
        userId: null,
        dateOfCommission: null
      },
      apexPie1: {
        options: {
          dataLabels: {
            enabled: false
          },
          colors: [config.light.primary, config.light.secondary, config.light.success, config.light.warning, config.light.info],
          labels: [],
          legend: {}
        },
        series: [4, 300, 300, 200],
      }


    }

  },
  methods: {
    async onLoad() {

      this.currencyList = await fetch('/api/exchangeRate/currencyList', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());

      console.log(this.currencyList)


      this.workPlace = await fetch('/api/workPlace/getActiveWorkPlace', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());


      this.currentCommission = await fetch('/api/exchangeJob/currentCommission', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());


      if (this.workPlace.cashRegisterId != null) {

        const balancePayload = {
          cashRegisterId: this.workPlace.cashRegisterId,
        }

        this.cashRegisterBalance = await fetch('/api/balance/calculateBalance', {
          method: 'POST',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
            "Content-Type": "application/json"
          },
          body: JSON.stringify(balancePayload),
        }).then((response) => response.json());


        this.weekCommissionRecords = await fetch('/api/exchangeJob/weekCommission', {
          method: 'GET',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
          }
        }).then((response) => response.json());

        this.mainApexArea.series[0].data = this.weekCommissionRecords.map(a => parseInt(a.sellCommission));
        this.mainApexArea.series[1].data = this.weekCommissionRecords.map(a => parseInt(a.buyCommission));
        this.mainApexArea.options.xaxis.categories = this.weekCommissionRecords.map(a => a.dateOfCommission);

        setTimeout(() => {
          this.apexLoading = false;
        });
      }

      this.workPlaceStatus = this.workPlace.workPlaceId !== null;
      console.log(this.workPlaceStatus)
    },
    getSellCommission() {

      console.log(4444)
      console.log(4444)
      console.log(this.mainApexSellCommission)
      console.log(4444)
      console.log(4444)

      return this.mainApexSellCommission;
    },
    initialize() {
      this.onLoad();
    },
    getCurrencyRecord(code) {
      return this.currencyList.find(d => d.currencyCode === code)
    },
    setApex1Labels() {

      console.log(222222)
      console.log(this.cashRegisterBalance)

      let series = [];

      for (let i = 0; i < this.cashRegisterBalance.balanceList.length; i++) {
        let y = Math.floor(Math.random() * (500 - 100 + 100)) + 100;
        this.apexPie1.options.labels.push(this.cashRegisterBalance.balanceList[i].currencyCode);
      }
      return series;
    },
    getApexPieLabels() {

      console.log(222222)
      console.log(this.cashRegisterBalance)

      let series = [];

      for (let i = 0; i < this.cashRegisterBalance.balanceList.length; i++) {
        let y = Math.floor(Math.random() * (500 - 100 + 100)) + 100;
        series.push(this.cashRegisterBalance.balanceList[i].currencyCode);
      }
      return series;
    },
    getApexPieSeries() {

      let series = [];

      for (let i = 0; i < this.cashRegisterBalance.balanceList.length; i++) {
        let y = Math.floor(Math.random() * (500 - 100 + 100)) + 100;
        series.push(this.cashRegisterBalance.balanceList[i].balance);
      }
      return series;
    },


    getRandomDataForTrends() {
      const arr = [];
      for (let i = 0; i < 12; i += 1) {
        arr.push(Math.random().toFixed(1) * 10);
      }
      return arr;
    },
    generatePieSeries() {
      let series = [];

      for (let i = 0; i < 5; i++) {
        let y = Math.floor(Math.random() * (500 - 100 + 100)) + 100;
        series.push(y);
      }
      return series;
    },
    getRandomInt(min, max) {
      let rand = min - 0.5 + Math.random() * (max - min + 1);
      return Math.round(rand);
    },
  },
  mounted() {
    this.onLoad();
    this.initialize();

  },
};
</script>

<style src="./Dashboard.scss" lang="scss"/>
