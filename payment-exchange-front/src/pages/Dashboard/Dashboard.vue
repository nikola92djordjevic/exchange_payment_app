<template>
  <v-container fluid>
    <v-overlay
      :absolute="absolute"
      v-model="overlay"
      contained
      class="align-center justify-center"
    >
      <v-row no-gutters height="90%">
        <v-col align="center" cols="12" sm="12" md="12" lg="12">
          <spacer/>
          <v-progress-circular
            :size="50"
            color="primary"
            indeterminate
          ></v-progress-circular>
          <spacer/>
        </v-col>
        <v-col align="center" cols="12" sm="12" md="12" lg="12">
          <spacer/>
          <h3 class="text-primary">
            Loading...
          </h3>
          <spacer/>
        </v-col>
      </v-row>
    </v-overlay>

    <v-row no-gutters height="100%">
      <v-container>

        <v-dialog v-model="dialogTransfer" max-width="1000px">
          <v-card>
            <v-card-title class="pa-6 text-h5">
              <v-row justify="center" align="center">
                <h1 class="page-title"> Transfer </h1>
              </v-row>
            </v-card-title>
            <v-divider/>
            <v-card-text class="pa-6 pt-5 mb-1">
              <v-row justify="center">
                <v-col
                  cols="10"
                  sm="6"
                  md="6"
                  align="end"
                >
                  <h1 class="page-title"> Select treasury: </h1>
                </v-col>
                <v-col
                  cols="10"
                  sm="4"
                  md="4"
                >
                  <v-select
                    label="Treasury"
                    :items="treasuries"
                    item-value="cashRegisterId"
                    :item-text="getSelectTreasuryText"
                    v-on:change="setSelectedTreasury"
                    v-model="selectedTreasury"
                    return-object
                    outlined
                  ></v-select>
                </v-col>
              </v-row>
              <v-row justify="center">
                <v-col
                  cols="12"
                  sm="6"
                  md="6"
                  align="end"
                >
                  <h1 class="page-title"> Select currency: </h1>
                </v-col>
                <v-col
                  cols="12"
                  sm="4"
                  md="4">
                  <v-select
                    label="Currency"
                    :items="selectedTreasuryBalance.balanceList"
                    item-value="currencyCode"
                    :item-text="getCurrencyItemText"
                    :disabled="this.selectedTreasuryBalance.cashRegisterId === null"
                    v-model="selectedBalance"
                    return-object
                    outlined
                  ></v-select>
                </v-col>
              </v-row>
              <v-row justify="center">
                <v-col
                  cols="10"
                  sm="6"
                  md="6"
                  align="end"
                >
                  <h1 class="page-title"> Available balance:</h1>
                </v-col>
                <v-col
                  cols="12"
                  sm="4"
                  md="4"
                  align="end">

                  <h1 class="page-title">
                    {{ Number(this.selectedBalance.balance).toFixed(2) + " " + getCurrencyName() }}</h1>
                </v-col>
              </v-row>
              <v-row justify="center">
                <v-col
                  cols="12"
                  sm="6"
                  md="6"
                  align="end"
                >
                  <h1 class="page-title"> Enter transfer amount:</h1>
                </v-col>
                <v-col
                  cols="12"
                  sm="4"
                  md="4">
                  <v-text-field
                    class="right-input zoom1"
                    v-model:value="transferAmount"
                    type="number"
                    hide-spin-buttons
                    label="Transfer Amount"
                    :disabled="this.selectedBalance.currencyCode === null"
                    @focus="$event.target.select()"
                    outlined
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-card-text>
            <v-divider/>
            <v-card-actions class="pa-6">
              <v-spacer></v-spacer>
              <v-btn color="blue primary" x-large @click="closeTransferDialog">Cancel</v-btn>
              <v-btn color="blue primary" x-large @click="transfer">Transfer</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-container>

      <v-row>
        <v-col lg="12" sm="12" md="12" cols="12">
          <v-card v-show="!workPlaceActive"
                  class="mx-3 mb-1 rounded-lg"
                  elevation="2"
                  outlined
                  height="100%">
            <v-card-title class="text-h5">

              <v-row no-gutters class="d-flex justify-center align-center">

                <h1 class="page-title center"> You don't have open work place, </h1>
                <h1 class="page-title center"> select cash register to open work place</h1>
              </v-row>

            </v-card-title>
            <v-card-text>

              <v-row no-gutters class="d-flex justify-center align-center">
                <v-col
                  cols="12"
                  sm="8"
                  md="6"
                >
                  <v-form ref="openWorkPlaceForm" v-model="validOpenWorkPlaceForm" lazy-validation>
                    <v-select
                      v-model="openWorkPlaceReq.cashRegisterId"
                      :rules="[rules.required]"
                      label="Cash Register"
                      :items="cashRegisters"
                      item-value="cashRegisterId"
                      :item-text="getSelectCashRegisterText"
                      outlined
                    ></v-select>
                  </v-form>
                </v-col>
              </v-row>


            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" x-large @click="openWorkPlace">Open work place</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>

          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col lg="12" sm="12" md="12" cols="12">

          <v-card v-show="workPlaceActive"
                  class="mx-3 mb-1 rounded-lg"
                  elevation="2"
                  outlined
                  height="100%">
            <v-card-title class="pa-6 pb-3">


            </v-card-title>
            <v-card-text class="pa-6 pt-0">
              <v-row>
                <v-col lg="4" sm="4" cols="12">

                  <v-col lg="12" sm="12" cols="12">
                    <v-card
                      elevation="2"
                      outlined>
                      <v-card-title>

                        <v-row>
                          <v-col lg="2" sm="2" cols="12">
                            <v-icon style="font-size: 80px">mdi-cash-register</v-icon>
                          </v-col>
                          <v-col lg="10" sm="01" cols="12" class="ps-7">
                            <v-row class="pa-3">
                              <h2>Id: {{ this.workPlace.cashRegisterId }}</h2>
                              <v-spacer></v-spacer>
                              <h2>Name: <br/> {{ this.cashRegisterBalance.cashRegisterName }}</h2>
                            </v-row>
                          </v-col>

                        </v-row>

                      </v-card-title>
                    </v-card>
                  </v-col>
                  <v-col lg="12" sm="12" cols="12">
                    <v-card class="support-requests mx-1 mb-1"
                            elevation="2"
                            outlined>
                      <v-card-title>
                        <v-row justify="center">
                          <h2>Today earned provision</h2>
                        </v-row>
                      </v-card-title>
                      <v-card-text class="pa-0">
                        <v-simple-table>
                          <template v-slot:default>
                            <tbody>
                            <tr>
                              <td>BUY</td>
                              <td>{{ Number(currentCommission.buyCommission).toFixed(2) }} RSD</td>
                            </tr>
                            <tr>
                              <td>SELL</td>
                              <td>{{ Number(currentCommission.sellCommission).toFixed(2) }} RSD</td>
                            </tr>
                            <tr>
                              <td>TOTAL</td>
                              <td>
                                {{
                                  Number(currentCommission.buyCommission + currentCommission.sellCommission).toFixed(2)
                                }}
                                RSD
                              </td>
                            </tr>
                            </tbody>
                          </template>
                        </v-simple-table>
                      </v-card-text>
                    </v-card>
                  </v-col>
                </v-col>
                <v-col lg="4" sm="4" cols="12">
                  <v-card class="support-requests mx-1 mb-1"
                          elevation="2"
                          outlined>
                    <v-card-title>
                      <v-row justify="center">
                        <h2>Cash Register balance</h2>
                      </v-row>
                    </v-card-title>
                    <v-card-text class="pa-0">
                      <v-simple-table>
                        <template v-slot:default>
                          <tbody>
                          <tr v-for="item in cashRegisterBalance.balanceList" :key="item.name">
                            <td class="zoom1 text-lg-right">{{ getBalanceCurrencyText(item) }}</td>
                          </tr>
                          </tbody>
                        </template>
                      </v-simple-table>
                    </v-card-text>
                  </v-card>
                </v-col>

                <v-col lg="4" sm="4" cols="12">
                  <v-col lg="12" sm="12" cols="12">
                    <v-card class="pa-7"
                            elevation="2"
                            outlined>
                      <v-row>
                        <v-col lg="2" sm="2" cols="12">
                          <v-icon style="font-size: 80px">mdi-desktop-tower-monitor</v-icon>
                        </v-col>
                        <v-col lg="10" sm="10" cols="12" class="ps-7">
                          <h2>Status: Open</h2>
                          <v-spacer></v-spacer>
                          <h2>Opened: <br/> {{ convertDateTime(this.workPlace.timestampOfOpen) }}</h2>
                        </v-col>
                      </v-row>
                      <v-row justify="center">
                        <v-btn
                          color="primary"
                          x-large
                          v-show="workPlaceActive"
                          @click="closeWorkPlace"
                        >
                          Close Work Place
                        </v-btn>
                      </v-row>
                    </v-card>
                  </v-col>

                  <v-col lg="12" sm="12" cols="12">
                    <v-card class="pa-7"
                            elevation="2"
                            outlined>
                      <v-row justify="center">

                        <v-icon style="font-size: 80px">mdi-safe</v-icon>

                        <h1 class="pa-7">Treasury</h1>
                      </v-row>


                      <v-row justify="center">

                        <v-btn
                          color="primary"
                          x-large
                          @click="showTransferDialog"
                        >
                          Transfer currency
                        </v-btn>
                      </v-row>
                    </v-card>
                  </v-col>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card
            class="mx-3 mb-1 rounded-lg"
            elevation="2"
            outlined
            height="100%">
            <v-card-title class="pa-6 pb-0">
              <v-row no-gutters>
                <v-col
                  cols="7"
                  sm="4"
                  md="4"
                  lg="5"
                  class="d-flex align-center"
                >
                  <v-row justify="center">
                    <h2>Earned provision per days</h2>
                  </v-row>
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
                        hide-details
                        :items="apexSelectWeek"
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
                    :options="
                mainApexAreaSelect === 'One week'
                ? this.mainApexArea.options
                : mainApexAreaSelect === 'Two week'
                ? this.mainApexArea.options2
                : mainApexAreaSelect === 'Three week'
                ? this.mainApexArea.options3
                : this.mainApexArea.options
                "
                    :series="
                      mainApexAreaSelect === 'One week'
                        ? this.mainApexArea.series
                        : mainApexAreaSelect === 'Two week'
                        ? this.mainApexArea.series2
                        : mainApexAreaSelect === 'Three week'
                        ? this.mainApexArea.series3
                        : this.mainApexArea.series
                    "
                  ></ApexChart>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-row>
  </v-container>

</template>

<script>
import Trend from "vuetrend";
import ApexChart from "vue-apexcharts";
import config from "@/config";

export default {
  name: "WorkPlaces",
  components: {
    Trend,
    ApexChart,
  },
  data() {
    return {
      absolute: true,
      overlay: false,
      headers: [
        {text: 'Currency', value: 'currencyCode', class: "bold--text title"},
        {text: 'Balance', value: 'balance'}
      ],
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
            size: [0, 0]
          },
          stroke: {
            width: [3, 3],
            curve: ['straight', 'straight']
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
            },
            labels: {
              style: {
                fontSize: '18px',
              },
            },
            tickAmount: 5
          },
          xaxis: {
            //imena colona
            categories: [],
            labels: {
              style: {
                fontSize: '18px',
              }
            },
          }
        },
        options2: {
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
            size: [0, 0]
          },
          stroke: {
            width: [3, 3],
            curve: ['straight', 'straight']
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
            },
            labels: {
              style: {
                fontSize: '18px',
              },
            },
            tickAmount: 5
          },
          xaxis: {
            //imena colona
            categories: [],
            labels: {
              style: {
                fontSize: '18px',
              }
            },
          }
        },
        options3: {
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
            size: [0, 0]
          },
          stroke: {
            width: [3, 3],
            curve: ['straight', 'straight']
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
            },
            labels: {
              style: {
                fontSize: '18px',
              },
            },
            tickAmount: 5
          },
          xaxis: {
            //imena colona
            categories: [],
            labels: {
              style: {
                fontSize: '18px',
              }
            },
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
        series3: [
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
      },
      apexLoading: true,
      value: this.getRandomInt(10, 90),
      value2: this.getRandomInt(10, 90),
      mainApexAreaSelect: "One week",
      apexSelectWeek: ['One week', 'Two week', 'Three week'],
      rules: {
        required: value => !!value || 'Required field',
      },
      validOpenWorkPlaceForm: true,
      dialogOpenWP: false,
      dialogTransfer: false,
      workPlace: {
        workPlaceId: null,
        cashRegisterId: null,
        userId: null,
        timestampOfOpen: null,
        timestampOfClose: null,
        timestampOfCreation: null,
      },
      emptyWorkPlace: {
        workPlaceId: null,
        cashRegisterId: null,
        userId: null,
        timestampOfOpen: null,
        timestampOfClose: null,
        timestampOfCreation: null,
      },
      workPlaceActive: false,
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
      cashRegisters: [{
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
      }],
      treasuries: [{
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
        flTreasury: null
      }],
      selectedTreasury: {
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
        flTreasury: null
      },
      emptyTreasury: {
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
        flTreasury: null
      },
      selectedTreasuryBalance: {
        cashRegisterId: null,
        cashRegisterName: null,
        balanceList: [{
          currencyCode: null,
          balance: 0
        }]
      },
      emptyTreasuryBalance: {
        cashRegisterId: null,
        cashRegisterName: null,
        balanceList: [{
          currencyCode: null,
          balance: 0
        }]
      },
      selectedBalance: {
        currencyCode: null,
        balance: 0
      },
      emptyBalance: {
        currencyCode: null,
        balance: 0
      },
      transferAmount: 0,
      openWorkPlaceReq: {
        cashRegisterId: null,
        name: null,
      },
      currentCommission: {
        buyCommission: 0,
        sellCommission: 0,
        totalCommission: 0,
        userId: null,
        dateOfCommission: null
      },
    }
  },

  watch: {
    dialogTransfer(val) {
      val || this.closeTransferDialog()
    },
  },
  /*created() {
    this.initialize()
  },*/
  mounted() {

    this.overlay=true;
    this.onLoad();

   // this.loadingPage=false;
  },

  methods: {
    async onLoad() {


      await fetch('/api/exchangeRate/currencyList', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (!response.ok) {
          this.pushErrorNotification("Something went wrong - Currency list");
          throw new Error('Something went wrong');
        } else {
          this.currencyList = await response.json();
        }
      });

      await fetch('/api/exchangeJob/currentCommission', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (response.ok) {
          this.currentCommission = await response.json();
        }
      });

      await fetch('/api/cashRegister/getAllFreeCashRegisters', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (!response.ok) {

          this.pushErrorNotification("Something went wrong - CashRegisters");
          throw new Error('Something went wrong');
        } else {
          this.cashRegisters = await response.json();
        }
      });

      await fetch('/api/cashRegister/getAllTreasuries', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (!response.ok) {

          this.pushErrorNotification("Something went wrong - CashRegisters");
          throw new Error('Something went wrong');
        } else {
          this.treasuries = await response.json();
        }
      });

      await fetch('/api/workPlace/getActiveWorkPlace', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (!response.ok) {
          if (!response.status === 404) {
            this.pushErrorNotification("Something went wrong - WorkPlace data");
            throw new Error('Something went wrong');
          } else {
            this.workPlace = Object.assign({}, this.emptyWorkPlace)
            this.workPlaceActive = this.workPlace.workPlaceId !== null;
          }
        } else {
          this.workPlace = await response.json();
          console.log(this.workPlaceActive)

          this.workPlaceActive = this.workPlace.workPlaceId !== null;

          console.log(this.workPlaceActive)

          console.log(this.workPlaceActive)
          //this.pushSuccessNotification("WorkPlace has closed");
        }
      });

      if (this.workPlace.cashRegisterId != null) {

        const balancePayload = {
          cashRegisterId: this.workPlace.cashRegisterId,
        }

        await fetch('/api/balance/calculateBalance', {
          method: 'POST',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
            "Content-Type": "application/json"
          },
          body: JSON.stringify(balancePayload),
        }).then(async (response) => {
          if (!response.ok) {
            this.pushErrorNotification("Something went wrong - WorkPlace data");
            throw new Error('Something went wrong');
          } else {
            this.cashRegisterBalance = await response.json();
            //this.pushSuccessNotification("WorkPlace has closed");
          }
        });
        console.log(this.cashRegisterBalance)
      }
      this.workPlaceActive = this.workPlace.workPlaceId !== null;
      console.log(this.workPlace)


      this.weekCommissionRecords = await fetch('/api/exchangeJob/calculateCommissionPerDay/7', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());
      this.mainApexArea.series[0].data = this.weekCommissionRecords.map(a => parseInt(a.sellCommission));
      this.mainApexArea.series[1].data = this.weekCommissionRecords.map(a => parseInt(a.buyCommission));
      this.mainApexArea.options.xaxis.categories = this.weekCommissionRecords.map(a => a.dateOfCommission);


      this.weekCommissionRecords = await fetch('/api/exchangeJob/calculateCommissionPerDay/14', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());
      this.mainApexArea.series2[0].data = this.weekCommissionRecords.map(a => parseInt(a.sellCommission));
      this.mainApexArea.series2[1].data = this.weekCommissionRecords.map(a => parseInt(a.buyCommission));
      this.mainApexArea.options2.xaxis.categories = this.weekCommissionRecords.map(a => a.dateOfCommission);


      this.weekCommissionRecords = await fetch('/api/exchangeJob/calculateCommissionPerDay/21', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());
      this.mainApexArea.series3[0].data = this.weekCommissionRecords.map(a => parseInt(a.sellCommission));
      this.mainApexArea.series3[1].data = this.weekCommissionRecords.map(a => parseInt(a.buyCommission));
      this.mainApexArea.options3.xaxis.categories = this.weekCommissionRecords.map(a => a.dateOfCommission);

      setTimeout(() => {
        this.apexLoading = false;
        this.overlay = false;
      });


    },
    pushSuccessNotification(message) {
      this.$toast.success(message, {
        position: "top-right",
        timeout: 3000,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        draggablePercent: 0.6,
        showCloseButtonOnHover: true,
        hideProgressBar: false,
        closeButton: "button",
        icon: true
      });
    },
    pushErrorNotification(message) {
      this.$toast.error(message, {
        position: "top-right",
        timeout: 3000,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        draggablePercent: 0.6,
        showCloseButtonOnHover: true,
        hideProgressBar: false,
        closeButton: "button",
        icon: true
      });
    },
    convertDateTime(item) {
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2) + "." + ("0" + (date.getMonth() + 1)).slice(-2) + "." + date.getFullYear() + " " +
        ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
    },
    convertDate(item) {
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2) + "." + ("0" + (date.getMonth() + 1)).slice(-2) + "." + date.getFullYear();
    },
    getBalanceCurrencyText(item) {
      return `${Number(item.balance).toFixed(2)} - ${this.getBalanceCurrencyName(item)}`;
    },
    showTransferDialog() {
      this.dialogTransfer = true;
    },
    closeTransferDialog() {
      this.dialogTransfer = false;
      this.selectedTreasury = Object.assign({}, this.emptyTreasury);
      this.selectedTreasuryBalance = Object.assign({}, this.emptyTreasuryBalance);
      this.resetTransferDialog();
    },
    resetTransferDialog() {
      this.selectedBalance = Object.assign({}, this.emptyBalance);
      this.transferAmount = 0;
    },
    openWorkPlaceDialog() {
      this.dialogOpenWP = true;
    },
    closeWorkPlaceDialog() {
      this.dialogOpenWP = false;
    },
    getRandomInt(min, max) {
      let rand = min - 0.5 + Math.random() * (max - min + 1);
      return Math.round(rand);
    },
    getCurrencyItemText(item) {
      if (item.currencyCode !== null) {
        let curr = this.currencyList.find(d => (String(d.currencyCode) === String(item.currencyCode)))
        return `${curr.currencyCode} - ${curr.currencyNameCode}`
      } else {
        return '';
      }
    },
    getCurrencyName() {
      if (this.selectedBalance.currencyCode !== null) {
        let curr = this.currencyList.find(d => (String(d.currencyCode) === String(this.selectedBalance.currencyCode)))
        return `${curr.currencyNameCode}`
      } else {
        return '';
      }
    },
    getBalanceCurrencyName(item) {
      let curr = this.currencyList.find(d => (String(d.currencyCode) === String(item.currencyCode)))
      return `${curr.currencyNameCode}`
    },
    getSelectCashRegisterText(item) {
      return `${item.cashRegisterId} - ${item.name}`;
    },
    getSelectTreasuryText(item) {
      return `${item.cashRegisterId} - ${item.name}`;
    },
    getSelectCurrencyText(item) {
      return `${item.currencyCode} - ${item.balance}`;
    },
    async setSelectedTreasury() {
      const balancePayload = {
        cashRegisterId: this.selectedTreasury.cashRegisterId,
      }
      await fetch('/api/balance/calculateBalance', {
        method: 'POST',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
          "Content-Type": "application/json"
        },
        body: JSON.stringify(balancePayload),
      }).then(async (response) => {
        if (!response.ok) {
          this.pushErrorNotification("Something went wrong - WorkPlace data");
          throw new Error('Something went wrong');
        } else {
          this.selectedTreasuryBalance = await response.json();
        }
      });

      this.resetTransferDialog();
    },
    closeWorkPlace() {

      const payload = {
        workPlaceId: this.workPlace.workPlaceId
      }

      console.log(payload);

      fetch("api/workPlace/close", {
        method: "POST",
        headers: {
          "Authorization": sessionStorage.getItem('user-token'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload),
      }).then((response) => {
        if (!response.ok) {
          this.initialize();
          this.pushErrorNotification("Something went wrong");
          throw new Error('Something went wrong');

        } else {
          this.pushSuccessNotification("WorkPlace has closed");
          this.initialize();
        }
      });
    },
    validForm() {
      return this.$refs.openWorkPlaceForm.validate()
    },
    openWorkPlace() {
      if (this.validForm()) {

        const payload = {
          cashRegisterId: this.openWorkPlaceReq.cashRegisterId,
          userId: sessionStorage.getItem('user-id'),
          timestampOfOpen: new Date().toISOString(),
          timestampOfCreation: new Date().toISOString(),
        }

        console.log(payload);

        fetch("api/workPlace/open", {
          method: "POST",
          headers: {
            "Authorization": sessionStorage.getItem('user-token'),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payload),
        }).then(async (response) => {
          if (!response.ok) {
            this.errorResp = await response.json();
            this.pushErrorNotification(this.errorResp.errorMessage);
            this.closeWorkPlaceDialog();
            throw new Error('Something went wrong');

          } else {
            this.pushSuccessNotification("WorkPlace has open");
            this.closeWorkPlaceDialog();
            this.initialize();
          }
        });
      }
    },
    transfer() {

      const payload = [{
        entrySide: 2,
        cashRegisterId: this.selectedTreasury.cashRegisterId,
        entryTypeId: 1,
        currencyCode: this.selectedBalance.currencyCode,
        amount: this.transferAmount,
        jobTypeId: 3,
        jobId: -1,
        workPlaceId: this.workPlace.workPlaceId,
        userId: sessionStorage.getItem('user-id'),
        timestampOfCreation: new Date().toISOString()
      },
        {
          entrySide: 1,
          cashRegisterId: this.workPlace.cashRegisterId,
          entryTypeId: 1,
          currencyCode: this.selectedBalance.currencyCode,
          amount: this.transferAmount,
          jobTypeId: 3,
          jobId: -1,
          workPlaceId: this.workPlace.workPlaceId,
          userId: sessionStorage.getItem('user-id'),
          timestampOfCreation: new Date().toISOString()
        },
      ]
      console.log(payload);

      fetch("api/balance/createEntries", {
        method: "POST",
        headers: {
          "Authorization": sessionStorage.getItem('user-token'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload),
      }).then(async (response) => {
        if (!response.ok) {
          this.errorResp = await response.json();
          this.pushErrorNotification(this.errorResp.errorMessage);
          this.closeTransferDialog();
          throw new Error('Something went wrong');

        } else {
          this.pushSuccessNotification("Transfer has successful");
          this.closeTransferDialog();
          this.initialize();
        }
      });
    },

    initialize() {
      this.onLoad();
    },
    getCurrencyRecord(code) {
      return this.currencyList.find(d => d.currencyCode === code)
    },
  }
}
</script>
<style scoped>

.icon {
  font-size: 150px;
}

.right-input >>> input {
  text-align: right;
}
</style>
