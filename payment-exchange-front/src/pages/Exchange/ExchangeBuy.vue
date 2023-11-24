<template>
  <v-container fluid height="100%">
    <v-overlay
      absolute
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
    <v-row position: relative no-gutters height="100%">
      <v-col lg="7" sm="6" md="6" cols="12">
        <v-card
          class="mx-3 mb-1 rounded-lg"
          elevation="2"
          outlined
          height="100%"
          v-show="workPlaceOpened"
        >
          <v-card-title center class="pa-6 pt-5 mb-1">
            <v-row justify="center" align="center">
              <h1 class="page-title"> Buy currency </h1>
            </v-row>
          </v-card-title>
          <v-card-text fluid class="pa-6 pt-15 mb-1">
            <v-form
              ref="exchangeForm"
              v-model="exchangeForm"
              lazy-validation>
              <v-row align="center">
                <v-col class="d-flex" cols="12" sm="6">
                  <v-select
                    label="Currency"
                    :items="exchangeRateResponse.exchangeRateEntryList"
                    item-value="exchangeRateEntryId"
                    :item-text="getItemText"
                    v-on:change="setDomesticAmount"
                    v-model="selectedExchangeRateEntry"
                    center
                    return-object
                    outlined
                    class="zoom1"
                  ></v-select>
                </v-col>

                <v-col class="d-flex" cols="12" sm="6">
                  <v-text-field
                    class="right-input zoom1"
                    v-model:value="changeAmount"
                    type="number"
                    hide-spin-buttons
                    :rules="[rules.positive]"
                    label="Exchange Amount"
                    :disabled="this.selectedCurrencyCode === null"
                    @focus="$event.target.select()"
                    prepend-inner-icon="mdi-cash-sync"
                    outlined
                    @input="setDomesticAmount"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row no-gutters justify="center">

                <v-col align="end" cols="12" sm="6">
                  <h3 class="subtext-index">Available amount for payout</h3>
                </v-col>
                <v-col align="end" cols="12" sm="6">
                  <h2 class="h1">
                    {{ this.availableDomesticBalance.toFixed(2) }} RSD
                  </h2>
                </v-col>
              </v-row>

              <v-row no-gutters align="center">

                <v-col align="end" cols="12" sm="6">
                  <h3 class="subtext-index" align="right">Amount for payout</h3>
                </v-col>
                <v-col align="end" cols="12" sm="6">
                  <h2 class="subtext"
                      align="right"
                      :class="{ 'zoom1 red--text': Number(this.availableDomesticBalance) < Number(this.domesticAmount)}">
                    {{ this.domesticAmount.toFixed(2) }} RSD
                  </h2>
                </v-col>

              </v-row>
              <v-row no-gutters align="center">
                <v-col class="d-flex" cols="12" sm="12">
                  <v-row justify="end">
                    <v-container>
                      <h3 class="subtext-index red--text"
                          align="center"
                          v-if="Number(this.availableDomesticBalance) < Number(this.domesticAmount)">
                        There is not enough money in the cash register</h3>
                      <h3 class="subtext-index red--text"
                          align="center"
                          v-if="Number(this.availableDomesticBalance) < Number(this.domesticAmount)">
                        Please transfer some money from treasury</h3>
                    </v-container>
                  </v-row>
                </v-col>

              </v-row>

              <v-row justify="center" align="center">

                <v-btn
                  color="primary"
                  @click="exchange"
                  :disabled="Number(domesticAmount) === 0 || Number(this.availableDomesticBalance) < Number(this.domesticAmount)"
                  x-large
                >
                  <v-icon>
                    mdi-swap-vertical
                  </v-icon>
                  Buy currency
                </v-btn>
              </v-row>
            </v-form>
          </v-card-text>

        </v-card>
        <v-card
          class="mx-3 mb-1 rounded-lg"
          elevation="2"
          outlined
          height="100%"
          v-show="!workPlaceOpened"
        >
          <v-container fill-height>
            <v-row justify="center" align="center">
              <h1 class="page-title"> Work Place is not opened! </h1>
            </v-row>
            <v-row justify="center" align="center">
              <v-btn
                color="primary"
                x-large
                @click="goToDashboardPage"
              >
                Open Work Place
              </v-btn>
            </v-row>
          </v-container>
        </v-card>

      </v-col>

      <v-dialog
        v-model="denominationDialog"
        max-width="1000px"
      >
        <v-card>
          <v-card-title>
            <v-row justify="center" class="pa-1 pt-3 mb-0">
              <span class="text-h5"><h1 class="page-title"> Enter Denomination </h1> </span>
            </v-row>
          </v-card-title>
          <v-divider/>
          <v-layout row justify-center no-gutters class="pa-0 pt-3 mb-1">
            <v-col cols="5" sm="5" md="5" justify-center>
              <v-container>
                <h3 class="subtext-index" align="right">Exchange amount</h3>
                <h1 class="subtext"
                    align="right"
                    :class="{ 'zoom1 red--text': Number(this.changeAmount) !== Number(this.denominationAmount),
                 'zoom1 green--text': Number(this.changeAmount) === Number(this.denominationAmount) }"
                >{{ this.changeAmount }}
                </h1>
              </v-container>
            </v-col>
            <v-col cols="1" sm="1" md="1" align="center" class="pt-12">
              <v-icon
                style="font-size: 60px"
                v-if="Number(this.changeAmount) === Number(this.denominationAmount)"
                color="green">
                mdi-equal
              </v-icon>
              <v-icon
                style="font-size: 60px"
                v-else
                color="red">
                mdi-not-equal-variant
              </v-icon>
            </v-col>
            <v-col cols="5" sm="5" md="5" justify-center>
              <v-container>
                <h3 class="subtext-index" align="left">Denomination amount</h3>
                <h1 class="subtext"
                    align="left"
                    :class="{ 'zoom1 red--text': Number(this.changeAmount) !== Number(this.denominationAmount),
                 'zoom1 green--text': Number(this.changeAmount) === Number(this.denominationAmount) }"
                >{{ this.denominationAmount }}
                </h1>
              </v-container>
            </v-col>
          </v-layout>
          <v-divider/>
          <v-card-text>
            <v-form
              ref="denominationForm"
              v-model="denominationForm"
              lazy-validation>
              <v-row class="d-flex justify-space-between">
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x5000"
                    :rules="[rules.positive, rules.required]"
                    label="x5000"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 5000"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x2000"
                    :rules="[rules.positive, rules.required]"
                    label="x2000"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 2000"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x1000"
                    :rules="[rules.positive, rules.required]"
                    label="x1000"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 1000"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x500"
                    :rules="[rules.positive, rules.required]"
                    label="x500"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 500"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x200"
                    :rules="[rules.positive, rules.required]"
                    label="x200"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 200"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x100"
                    :rules="[rules.positive, rules.required]"
                    label="x100"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 100"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x50"
                    :rules="[rules.positive, rules.required]"
                    label="x50"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 50"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x20"
                    :rules="[rules.positive, rules.required]"
                    label="x20"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 20"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x10"
                    :rules="[rules.positive, rules.required]"
                    label="x10"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 10"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x5"
                    :rules="[rules.positive, rules.required]"
                    label="x5"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 5"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x2"
                    :rules="[rules.positive, rules.required]"
                    label="x2"
                    prepend-inner-icon="mdi-cash"
                    type="number"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 2"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4" md="4" lg="4">
                  <v-text-field
                    v-model:value="denominationEntry.x1"
                    :rules="[rules.positive, rules.required]"
                    type="number"
                    label="x1"
                    prepend-inner-icon="mdi-cash"
                    outlined
                    @input="setDenominationAmount"
                    @focus="$event.target.select()"
                    v-show="maxBill >= 1"
                    class="zoom1"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-form>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              x-large
              @click="closeDenominationDialog"
            >
              Cancel
            </v-btn>
            <v-btn
              color="primary"
              x-large
              :disabled="Number(this.changeAmount) !== Number(this.denominationAmount)"
              @click="makeExchange"
            >
              Make exchange
            </v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-dialog>


      <v-col lg="5" sm="67" md="6" cols="12">
        <v-card class="mx-1 mb-1 rounded-lg" elevation="2" outlined height="100%">
          <v-card-title>
            <h1 class="page-title">Exchange rate</h1>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="exchangeRateHeaders"
            :items="exchangeRateResponse.exchangeRateEntryList"
            :search="search"
            class="table-striped"

            :item-class="markSelectedItem"
          >
            <template v-slot:item.buyRate="{item}">
              {{ getFixDigits(item.buyRate) }}
            </template>
            <template v-slot:item.middleRate="{item}">
              {{ getFixDigits(item.middleRate) }}
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card class="mx-1 mb-1 rounded-lg" elevation="2" outlined height="100%">
          <v-card-title>
            <h1 class="page-title">Last exchanges</h1>
          </v-card-title>
          <v-data-table
            :headers="exchangeRecordsHeaders"
            :items="exchangeRecordsResponse"
            class="table-striped"
          >
            <template v-slot:item.receivedCurrencyCode="{item}">
              {{ getCurrencyItemText(item.receivedCurrencyCode) }}
            </template>
            <template v-slot:item.receivedAmount="{item}">
              {{ getFixDigits(item.receivedAmount) }}{{ getCurrencyNameText(item.receivedCurrencyCode) }}
            </template>
            <template v-slot:item.exchangeAmount="{item}">
              {{ getFixDigits(item.exchangeAmount) }} RSD
            </template>
            <template v-slot:item.jobTypeId="{item}">
              {{ getExchangeType(item.jobTypeId) }}
            </template>
            <template v-slot:item.earnedCommission="{item}">
              {{ getEarnedCommission(item) }} RSD
            </template>
            <template v-slot:item.timestampOfCreation="{item}">
              {{ convertDateTime(item.timestampOfCreation) }}
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>

export default {
  computed: {
    formTitle() {
      this.editedIndex === -1 ? this.dialogNewUser = true : this.dialogNewUser = false
      return this.editedIndex === -1 ? 'New User' : 'Edit User'
    },
  },

  watch: {
    denominationDialog(val) {
      val || this.closeDenominationDialog()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  name: "Exchange.vue",
  data() {
    return {
      overlay: false,
      editedIndex: -1,
      denominationDialog: false,
      workPlaceOpened: false,
      exchangeForm: true,
      denominationForm: true,
      search: '',
      exchangeRateHeaders: [
        {text: 'Code', sortable: false, value: 'currencyCode'},
        {text: 'Name', sortable: false, value: 'currencyNameCode'},
        {text: 'Buy rate', sortable: false, value: 'buyRate', align: 'right'},
        {text: 'Middle rate', sortable: false, value: 'middleRate', align: 'right'},
        //{text: 'Sell rate', sortable: false, value: 'sellRate', align: 'right'},
      ],
      exchangeRecordsHeaders: [
        {text: 'ID', sortable: false, value: 'exchangeJobId'},
        {text: 'Type', sortable: false, value: 'jobTypeId'},
        {text: 'Currency amount', sortable: false, value: 'receivedAmount', align: 'right'},
        //{text: 'Received currency', sortable: false, value: 'receivedCurrencyCode', align: 'right'},
        {text: 'Domestic amount', sortable: false, value: 'exchangeAmount', align: 'right'},
        {text: 'Earned commission', sortable: false, value: 'earnedCommission', align: 'right'},
        {text: 'Time of exchange', sortable: false, value: 'timestampOfCreation', align: 'right'},
      ],
      selectedCurrencyCode: null,
      exchangeRateResponse: {
        exchangeRate: null,
        exchangeRateEntryList: [{
          exchangeRateEntryId: null,
          exchangeRateId: null,
          currencyCode: null,
          currencyNameCode: null,
          buyRate: null,
          middleRate: null,
          sellRate: null,
          timestampOfCreating: null
        }],
      },
      exchangeRecordsResponse: [{
        exchangeJobId: null,
        jobTypeId: null,
        receivedAmount: null,
        receivedCurrencyCode: null,
        exchangeRateEntryId: null,
        middleRateAmount: null,
        exchangeAmount: null,
        exchangeCashDenominationId: null,
        userId: null,
        timestampOfCreation: null,
        dateOfCreation: null,
        timeOfCreation: null
      }],
      activeWorkPlace: {
        workPlaceId: null,
        cashRegisterId: null,
        userId: null,
        timestampOfOpen: null,
        timestampOfClose: null,
        timestampOfCreation: null,
      },
      selectedExchangeRateEntry: {
        exchangeRateEntryId: null,
        exchangeRateId: null,
        currencyCode: null,
        currencyNameCode: null,
        buyRate: null,
        middleRate: null,
        sellRate: null,
        timestampOfCreating: null,
      },
      emptyExchangeRateEntry: {
        exchangeRateEntryId: null,
        exchangeRateId: null,
        currencyCode: null,
        currencyNameCode: null,
        buyRate: null,
        middleRate: null,
        sellRate: null,
        timestampOfCreating: null,
      },
      maxBill: null,
      availableDomesticBalance: 0,
      changeAmount: 0,
      domesticAmount: 0,
      denominationEntry: {
        x5000: 0,
        x2000: 0,
        x1000: 0,
        x500: 0,
        x200: 0,
        x100: 0,
        x50: 0,
        x20: 0,
        x10: 0,
        x5: 0,
        x2: 0,
        x1: 0,
      },
      emptyDenominationEntry: {
        x5000: 0,
        x2000: 0,
        x1000: 0,
        x500: 0,
        x200: 0,
        x100: 0,
        x50: 0,
        x20: 0,
        x10: 0,
        x5: 0,
        x2: 0,
        x1: 0,
      },
      currencyList: [{
        currencyCode: null,
        currencyNameCode: null,
        fullName: null,
        quantity: null,
        country: null,
        signLogo: null,
      }],
      activeCashRegisterBalance: {
        cashRegisterId: null,
        cashRegisterName: null,
        balanceList: [{
          currencyCode: null,
          balance: Number
        }]
      },
      denominationAmount: 0,
      rules: {
        equal: this.changeAmount == this.denominationAmount || 'Equal field',
        required: value => !!value || 'Required field',
        counter: value => value.length <= 20 || 'Max 20 characters',
        positive: value => {
          const pattern = /^[0-9]{0,5}$/
          return pattern.test(value) || 'Please insert positive number'
        },
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }

      },
    }
  },
  mounted() {
    this.onLoad()
  },
  methods: {
    async checkWorkPlace() {
      this.overlay = true;

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
            this.workPlaceOpened = this.activeWorkPlace.workPlaceId !== null;
            return false;
          }
        } else {
          this.activeWorkPlace = await response.json();
          this.workPlaceOpened = this.activeWorkPlace.workPlaceId !== null;

          console.log(this.workPlaceOpened);
          return true;
        }
      });
    },
    async onLoad() {
      this.overlay = true;

      if (this.checkWorkPlace()) {

        this.currencyList = await fetch('/api/exchangeRate/currencyList', {
          method: 'GET',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
          }
        }).then((response) => response.json());

        this.exchangeRateResponse = await fetch('/api/exchangeRate/getActiveExchangeRate', {
          method: 'GET',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
          }
        }).then((response) => response.json())

        this.exchangeRecordsResponse = await fetch('/api/exchangeJob/getExchangeJobRecords', {
          method: 'GET',
          headers: {
            Authorization: sessionStorage.getItem('user-token'),
            Accept: 'application/json',
          }
        }).then((response) => response.json());

        const balancePayload = {
          cashRegisterId: this.activeWorkPlace.cashRegisterId
        }
        this.activeCashRegisterBalance = await fetch('/api/balance/calculateBalance', {
          method: "POST",
          headers: {
            "Authorization": sessionStorage.getItem('user-token'),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(balancePayload),
        }).then((response) => response.json());

        this.setAvailableBalance();
      }

      setTimeout(() => {
        this.overlay = false;
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
    getFixDigits(item) {
      return Number(item).toFixed(2)
    },
    getItemText(item) {
      return `${item.currencyCode} - ${item.currencyNameCode}`;
    },
    validExchangeForm() {
      return this.$refs.exchangeForm.validate()
    },
    validDenominationForm() {
      return this.$refs.denominationForm.validate()
    },
    setDomesticAmount() {
      if (this.validExchangeForm()) {
        this.domesticAmount = Math.round(this.selectedExchangeRateEntry.buyRate * this.changeAmount * 100) / 100;
        this.selectedCurrencyCode = this.selectedExchangeRateEntry.currencyCode;
        const ind = this.currencyList.findIndex(d => d.currencyCode === this.selectedExchangeRateEntry.currencyCode)
        this.maxBill = this.currencyList[ind].quantity
      }
    },
    setAvailableBalance() {
      const balance = this.activeCashRegisterBalance.balanceList.find(d => d.currencyCode === "941")
      this.availableDomesticBalance = balance.balance;
    },
    getExchangeType(id) {
      if (id === 1) {
        return 'BUY';
      } else {
        return 'SELL';
      }
    },
    getEarnedCommission(item) {
      if (item.jobTypeId === 1) {
        return (item.middleRateAmount - item.exchangeAmount).toFixed(2);
      } else {
        return (item.exchangeAmount - item.middleRateAmount).toFixed(2);
      }
    },
    getCurrencyItemText(code) {
      let curr = this.currencyList.find(d => d.currencyCode == code)
      return `${curr.currencyCode} - ${curr.currencyNameCode}`
    },
    getCurrencyNameText(code) {
      let curr = this.currencyList.find(d => d.currencyCode == code)
      return ` ${curr.currencyNameCode}`
    },
    goToDashboardPage() {
      this.$router.push({name: 'Dashboard'})
    },
    initialize() {
      this.onLoad();
    },
    mounted() {
      this.onLoad()
    },
    resetDomesticAmount() {
      this.domesticAmount = null;
    },
    resetBuyCurrency() {
      this.selectedExchangeRateEntry = Object.assign({}, this.emptyExchangeRateEntry);
      this.selectedCurrencyCode = null;
      this.changeAmount = 0;
      this.domesticAmount = 0;
    },
    activerow: function (item) {
      this.selectedId = item.currencyCode;
      this.$set(item, 'selected', true)
    },
    exchange() {
      this.denominationDialog = true;
    },
    setDenominationAmount() {
      this.denominationAmount = (this.denominationEntry.x1 * 1) + (this.denominationEntry.x2 * 2) + (this.denominationEntry.x5 * 5)
        + (this.denominationEntry.x10 * 10) + (this.denominationEntry.x20 * 20) + (this.denominationEntry.x50 * 50)
        + (this.denominationEntry.x100 * 100) + (this.denominationEntry.x200 * 200) + (this.denominationEntry.x500 * 500)
        + (this.denominationEntry.x1000 * 1000) + (this.denominationEntry.x2000 * 2000) + (this.denominationEntry.x5000 * 5000);
    },
    closeDenominationDialog() {
      this.denominationDialog = false
      this.denominationAmount = 0;
      this.$nextTick(() => {
        this.denominationEntry = Object.assign({}, this.emptyDenominationEntry)
        this.editedIndex = -1
      })
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
    markSelectedItem: function (item) {
      return String(item.currencyCode) === String(this.selectedCurrencyCode) ? 'style-1' : null
    },
    async makeExchange() {
      //if (this.validDenominationForm()) {

      const payload = {
        exchangeJob: {
          exchangeJobId: null,
          jobTypeId: 1,//buy currency type
          receivedAmount: this.changeAmount,
          receivedCurrencyCode: this.selectedExchangeRateEntry.currencyCode,
          exchangeRateEntryId: this.selectedExchangeRateEntry.exchangeRateEntryId,
          middleRateAmount: (this.selectedExchangeRateEntry.middleRate * this.changeAmount),
          exchangeAmount: (this.selectedExchangeRateEntry.buyRate * this.changeAmount),
          userId: sessionStorage.getItem('user-id'),
          timestampOfCreation: new Date().toISOString(),
          dateOfCreation: new Date().toISOString().slice(0, 10),
          timeOfCreation: new Date().toLocaleTimeString('en-US', {hour12: false})
        },
        exchangeCashDenomination: {
          exchangeCashDenominationId: null,
          exchangeJobId: null,
          x1: this.denominationEntry.x1,
          x2: this.denominationEntry.x2,
          x5: this.denominationEntry.x5,
          x10: this.denominationEntry.x10,
          x20: this.denominationEntry.x20,
          x50: this.denominationEntry.x50,
          x100: this.denominationEntry.x100,
          x200: this.denominationEntry.x200,
          x500: this.denominationEntry.x500,
          x1000: this.denominationEntry.x1000,
          x2000: this.denominationEntry.x2000,
          x5000: this.denominationEntry.x5000
        },
        balanceEntryList: [{
          balanceEntryId: null,
          entrySide: 1,
          cashRegisterId: this.activeWorkPlace.cashRegisterId,
          entryTypeId: 1,
          currencyCode: this.selectedExchangeRateEntry.currencyCode,
          amount: this.changeAmount,
          jobTypeId: 1,
          jobId: null,
          workPlaceId: this.activeWorkPlace.workPlaceId,
          userId: sessionStorage.getItem('user-id'),
          timestampOfCreation: new Date().toISOString()
        },
          {
            balanceEntryId: null,
            entrySide: 2,
            cashRegisterId: this.activeWorkPlace.cashRegisterId,
            entryTypeId: 1,
            currencyCode: 941,
            amount: this.domesticAmount,
            jobTypeId: 1,
            jobId: null,
            workPlaceId: this.activeWorkPlace.workPlaceId,
            userId: sessionStorage.getItem('user-id'),
            timestampOfCreation: new Date().toISOString()
          }
        ]
      }

      console.log(payload);
      await fetch("api/exchangeJob/exchangeMoney", {
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
          this.closeDenominationDialog();
          this.resetBuyCurrency();
          this.initialize();
        } else {
          this.pushSuccessNotification("Buy currency has successful");
          this.closeDenominationDialog();
          this.resetBuyCurrency();
          this.initialize();
        }
      });
      //}
    }

  }
}
</script>


<style>

.zoom1.v-input input {
  font-size: 1.5em;
}

.zoom1.v-input label {
  font-size: 1.2em;
}

.v-select__selection {

  font-size: 1.5em !important;
}


.v-list-item__title {

  font-size: 1.5em !important;
}

.v-data-table-header {
  background-color: #cef6ea;
}

table tr > td {
  border: 1px solid #cee7f6;
}

table tr > th {
  border: 1px solid #cee7f6;
}


.v-data-table > .v-data-table__wrapper > table > tbody > tr > td,
.v-data-table > .v-data-table__wrapper > table > thead > tr > th,
.v-data-table > .v-data-table__wrapper > table > tfoot > tr > th {
  font-size: 1.3em !important;
}

.v-data-footer {
  font-size: 1.3em !important;

}

.v-data-table tbody tr.style-1 {
  background: #f5c17d70 !important;
}

.v-data-table tbody tr.style-1:hover {
  background: #ffd296d2 !important;
}
</style>

<style scoped>

.right-input >>> input {
  text-align: right;
}

.my-text-style-red >>> .v-text-field__slot input {
  color: red;
}

.my-text-style-green >>> .v-text-field__slot input {
  color: green;
}

.green-inp-field {
  color: green;
}
</style>
