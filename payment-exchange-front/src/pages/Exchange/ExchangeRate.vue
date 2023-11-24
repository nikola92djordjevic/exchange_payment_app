<template>
  <v-container fluid>
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
    <div class="exchangeRate-page">

      <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
        <h1 class="page-title">Exchange Rate</h1>

        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              rounded
              x-large
              color="primary"
              class="text-capitalize button-shadow mr-1"
              @click="showCreateDialog()"
            >
              <v-icon dark>
                mdi-plus
              </v-icon>
              New Exchange Rate
            </v-btn
            >
          </template>
        </v-menu>
        <v-dialog v-model="dialogCreate" max-width="1500px">
          <v-card>
            <v-card-title class="page-title">Create dialog</v-card-title>

            <v-card-text>
              <v-form
                ref="currencyRateForm"
                v-model="validCurrencyRateForm"
                lazy-validation
              >
                <v-layout row justify-center>
                  <v-col cols="12" sm="2" md="2">
                    <v-text-field
                      v-model="editedExchangeRate.exchangeRateId"
                      label="Exchange Rate Id"
                      class="zoom1"
                      outlined
                      disabled
                    ></v-text-field>
                  </v-col>
                </v-layout>
                <v-layout row justify-space-around>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-menu
                      v-model="menu1"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="editedExchangeRate.timestampOfStart"
                          label="Date of start"
                          :rules="[rules.required]"
                          prepend-inner-icon="mdi-calendar"
                          class="zoom1"
                          readonly
                          outlined
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="editedExchangeRate.timestampOfStart"
                        @input="menu1 = false"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>

                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-menu
                      v-model="menu"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >

                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="editedExchangeRate.timestampOfEnd"
                          label="Date of end"
                          :rules="[rules.required]"
                          prepend-inner-icon="mdi-calendar"
                          class="zoom1"
                          readonly
                          outlined
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="editedExchangeRate.timestampOfEnd"
                        @input="menu = false"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>
                </v-layout>

                <v-layout row>
                  <v-col>
                    <div class="work-experiences">
                      <div v-for="(exchRtRow, index) in exchangeRateRow" :key="index">
                        <v-layout row>
                          <v-col lg="1" md="2" sm="6" cols="12">

                            <v-text-field v-model="exchRtRow.exchangeRateEntryId"
                                          outlined
                                          disabled
                                          :name="`exchangeRateRow[${index}][exchangeRateEntryId]`"
                                          type="text"
                                          class="form-control mx-1 mb-1 zoom1"
                                          label="Entry id">

                            </v-text-field>
                          </v-col>

                          <v-col lg="2" md="3" sm="6" cols="12">

                            <v-select
                              label="Currency Code"
                              class="form-control zoom1"
                              :items="currencyList"
                              :options="availableCurrencyList"
                              item-value="currencyCode"
                              :item-text="getCurrencyItemText"
                              v-model="exchRtRow.currencyCode"
                              v-on:change="setCurrencyNameCode(exchRtRow,exchRtRow.currencyCode)"
                              center
                              outlined
                              required
                            ></v-select>
                          </v-col>

                          <v-col lg="2" md="3" sm="6" cols="12">

                            <v-text-field v-model="exchRtRow.currencyNameCode"
                                          outlined
                                          disabled
                                          :rules="[rules.required]"
                                          type="text"
                                          class="form-control zoom1"
                                          label="Currency Name code"></v-text-field>
                          </v-col>

                          <v-col lg="2" md="3" sm="6" cols="12">

                            <v-text-field v-model="exchRtRow.buyRate"
                                          outlined
                                          required
                                          :rules="[rules.required]"
                                          :counter="10"
                                          type="number"
                                          hide-spin-buttons
                                          class="right-input form-control zoom1"
                                          label="Buy rate"></v-text-field>
                          </v-col>

                          <v-col lg="2" md="3" sm="6" cols="12">

                            <v-text-field v-model="exchRtRow.middleRate"
                                          outlined
                                          required
                                          :rules="[rules.required]"
                                          :counter="10"
                                          type="number"
                                          hide-spin-buttons
                                          class="right-input form-control zoom1"
                                          label="Middle Rate"></v-text-field>
                          </v-col>

                          <v-col lg="2" md="3" sm="6" cols="12">

                            <v-text-field v-model="exchRtRow.sellRate"
                                          outlined
                                          required
                                          :rules="[rules.required]"
                                          :counter="10"
                                          type="number"
                                          hide-spin-buttons
                                          class="right-input form-control zoom1"
                                          label="Sell rate"></v-text-field>
                          </v-col>

                          <v-col lg="1" md="2" sm="6" cols="12">
                            <v-hover v-slot="{ hover }">

                              <v-btn
                                class="ma-2"
                                text
                                icon
                                color="red lighten-2"

                                @click="deleteExchangeRateRow(index)"
                              >
                                <v-icon x-large>mdi-delete</v-icon>
                              </v-btn>
                            </v-hover>
                          </v-col>


                        </v-layout>
                      </div>
                    </div>

                  </v-col>


                </v-layout>

                <v-layout row justify-center>
                  <div class="form-group">
                    <v-btn :disabled="this.exchangeRateRowCount === this.currencyList.length"
                           @click="addExchangeRateRow"
                           class="btn secondary">
                      <v-icon> mdi-plus</v-icon>
                      Add New Currency
                    </v-btn>
                  </div>
                </v-layout>
              </v-form>

            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" x-large @click="closeEditDialog">Cancel</v-btn>
<!--              <v-btn color="primary" @click="validForm">Valid</v-btn>-->
              <v-btn color="primary" x-large v-show="createNew" @click="createExchangeRate">Create</v-btn>
              <v-btn color="primary" x-large v-show="!createNew" @click="editExchangeRate">Edit</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card><v-card-title class="text-h5">
            <v-row justify="center" align="center">
              <h5 class="text-h5">Are you sure you want to delete this item?</h5>
            </v-row>
          </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDeleteDialog">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>


      </v-row>
      <v-row>

        <v-col cols="12">
          <v-card class="mx-1 mb-1">
            <v-data-table
              :headers="headers"
              :items="exchangeRatesList"
              sort-by="exchangeRate.timestampOfStart"
              :sort-desc.sync="sortDesc"
              :single-expand="singleExpand"
              :expanded.sync="expanded"
              item-key="exchangeRate.exchangeRateId"
              show-expand
            >
              <template v-slot:item.exchangeRate.timestampOfStart="{item}">
                {{convertDate(item.exchangeRate.timestampOfStart)}}
              </template>
              <template v-slot:item.exchangeRate.timestampOfEnd="{item}">
                {{convertDate(item.exchangeRate.timestampOfEnd)}}
              </template>
              <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length">
                  <v-data-table
                    :headers="headers2"
                    :items="item.exchangeRateEntryList"
                    hide-default-footer
                  >
                    <template v-slot:item.buyRate="{item}">
                      {{ (item.buyRate).toFixed(2) }}
                    </template>
                    <template v-slot:item.middleRate="{item}">
                      {{ (item.middleRate).toFixed(2) }}
                    </template>
                    <template v-slot:item.sellRate="{item}">
                      {{ (item.sellRate).toFixed(2) }}
                    </template>
                  </v-data-table>

                </td>
              </template>
              <template v-slot:item.actions="{ item }">
                <v-icon
                  large
                  class="mr-2"
                  @click="editItem(item)"
                >
                  mdi-pencil
                </v-icon>
                <v-icon
                  large
                  @click="deleteItem(item)"
                >
                  mdi-delete
                </v-icon>
              </template>
              <template v-slot:no-data>
                <v-btn
                  color="primary"
                  @click="initialize"
                >
                  Reset
                </v-btn>
              </template>
            </v-data-table>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import dateFormatting from "@/filters/dateFormatting";

export default {
  name: "ExchangeRatePage",
  data() {
    return {
      overlay: false,
      sortBy: 'exchangeRate.exchangeRateId',
      sortDesc: true,
      headers: [
        {text: 'List Id', value: 'exchangeRate.exchangeRateId'},
        {text: 'Date of Start', value: 'exchangeRate.timestampOfStart'},
        {text: 'Date of End', value: 'exchangeRate.timestampOfEnd'},
        /*{text: 'FlActive', value: 'exchangeRate.flActive'},
        {text: 'Created by', value: 'exchangeRate.createdBy'},
        {text: 'Created', value: 'exchangeRate.timestampOfCreation'},
        {text: 'Changed by', value: 'exchangeRate.changedBy'},
        {text: 'Changed', value: 'exchangeRate.timestampOfChange'},*/
        {text: 'Actions', value: 'actions', sortable: false},
        {text: 'Additional data', value: 'data-table-expand'},
      ],
      headers2: [
        {text: 'CurrencyCode', value: 'currencyCode'},
        {text: 'CurrencyNameCode', value: 'currencyNameCode'},
        {text: 'Buy Rate', value: 'buyRate', align: 'right'},
        {text: 'Middle Rate', value: 'middleRate', align: 'right'},
        {text: 'Sell Rate', value: 'sellRate', align: 'right'},
      ],
      numberInputRule: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      expanded: [],
      singleExpand: true,
      exchangeRatesList: [{
        exchangeRate: {
          exchangeRateId: null,
          timestampOfStart: null,
          timestampOfEnd: null,
          flActive: null,
          createdBy: null,
          timestampOfCreation: null,
          changedBy: null,
          timestampOfChange: null,
        },
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
      }],
      editedExchangeRatesList: {
        exchangeRate: {
          exchangeRateId: null,
          timestampOfStart: null,
          timestampOfEnd: null,
          flActive: null,
          createdBy: null,
          timestampOfCreation: null,
          changedBy: null,
          timestampOfChange: null,
        },
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
      editedExchangeRate: {
        exchangeRateId: null,
        timestampOfStart: null,
        timestampOfEnd: null,
        flActive: null,
        createdBy: null,
        timestampOfCreation: null,
        changedBy: null,
        timestampOfChange: null,
      },
      emptyExchangeRate: {
        exchangeRateId: null,
        timestampOfStart: null,
        timestampOfEnd: null,
        flActive: null,
        createdBy: null,
        timestampOfCreation: null,
        changedBy: null,
        timestampOfChange: null,
      },
      currencyList: [{
        currencyCode: null,
        currencyNameCode: null,
        fullName: null,
        quantity: null,
        country: null,
        signLogo: null,
      }],
      availableCurrencyList: [{
        currencyCode: null,
        currencyNameCode: null,
        fullName: null,
        quantity: null,
        country: null,
        signLogo: null,
      }],
      validCurrencyRateForm: true,
      showAddCurrencyBtn: true,
      menu: false,
      menu1: false,
      dialogDelete: false,
      dialogCreate: false,
      createNew: false,
      editedIndex: -1,
      deleteIndex: -1,
      dialog: false,
      rules: {
        required: value => !!value || 'Required field',
        counter: value => value.length <= 20 || 'Max 20 characters',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }
      },
      exchangeRateRowCount: 1,
      exchangeRateRow: [{
        exchangeRateEntryId: null,
        exchangeRateId: null,
        currencyCode: null,
        currencyNameCode: null,
        buyRate: null,
        middleRate: null,
        sellRate: null,
        timestampOfCreating: null
      }],
      emptyExchangeRateRow: [{
        exchangeRateEntryId: null,
        exchangeRateId: null,
        currencyCode: null,
        currencyNameCode: null,
        buyRate: null,
        middleRate: null,
        sellRate: null,
        timestampOfCreating: null
      }]
    }
  },

  watch: {
    dialogCreate(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  mounted() {
    this.onLoad()
  },
  methods: {

    async onLoad() {
      this.overlay = true;

      this.exchangeRatesList = await fetch('/api/exchangeRate/getAllExchangeRates', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());


      this.currencyList = await fetch('/api/exchangeRate/currencyList', {
        method: 'GET',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then((response) => response.json());

      setTimeout(() => {
        this.overlay = false;
      });
    },
    convertDateTime(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2)   + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear() + " " +
        ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
    },
    convertDate(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2) + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear();
    },
    removeDomesticCurrency(){

      const ind = this.currencyList.findIndex(d => (d.currencyCode === '941'))
      this.currencyList.splice(ind, 1)
    },
    getCurrencyItemText(item) {
      console.log(111111);
      console.log(this.currencyList);
      console.log(this.availableCurrencyList);
      return `${item.currencyCode} - ${item.currencyNameCode}`;
    },
    setCurrencyNameCode(item, value){
      const ind = this.currencyList.findIndex(d => (d.currencyCode === value))
      const ind2 = this.availableCurrencyList.findIndex(d => (d.currencyCode === value))
      this.availableCurrencyList.splice(ind2,1);
      console.log(this.currencyList);
      item.currencyNameCode = this.currencyList[ind].currencyNameCode;
    },
    initialize() {
      this.onLoad();
    },
    deleteItem(item) {
      this.deleteIndex = this.exchangeRatesList.indexOf(item)
      this.editedUser = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteItemConfirm() {

      await fetch('/api/exchangeRate/delete/' + this.exchangeRatesList.at(this.deleteIndex).exchangeRate.exchangeRateId, {
        method: 'DELETE',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {

        if (!response.ok) {
          this.errorResp = await response.json();
          this.pushErrorNotification(this.errorResp.errorMessage);
        } else {
          this.pushSuccessNotification("The exchangeRate was deleted");
          this.exchangeRatesList.splice(this.deleteIndex, 1)
        }
      });
      this.closeDeleteDialog();
    },
    close() {
      this.dialog = false
      this.exchangeRateRow = this.emptyExchangeRateRow;
      this.exchangeRateRowCount = 1;
      this.$refs.currencyRateForm.reset()

      this.$nextTick(() => {
        this.editedUser = Object.assign({}, this.exchangeRatesList)
        this.editedIndex = -1
      })
    },
    closeDeleteDialog() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedUser = Object.assign({}, this.editedExchangeRate)
        this.deleteIndex = -1
      })
    },
    editItem(item) {
      this.dialogCreate = true;
      this.createNew = false;
      this.exchangeRateRowCount = item.exchangeRateEntryList.length;
      console.log(item);
      this.editedExchangeRate = Object.assign({}, item.exchangeRate);
      this.exchangeRateRow = JSON.parse(JSON.stringify(item.exchangeRateEntryList));

    },
    closeEditDialog() {
      this.dialogCreate = false
      this.$nextTick(() => {
        this.editedUser = Object.assign({}, this.editedExchangeRate)
        this.editedIndex = -1
      })
    },
    showCreateDialog() {

      this.availableCurrencyList =  JSON.parse(JSON.stringify(this.currencyList))

      console.log(112323231111);
      console.log(this.currencyList);
      console.log(this.availableCurrencyList);
      this.dialogCreate = true;
      this.createNew = true;
    },
    closeCreateDialog() {
      this.dialogCreate = false;
      this.createNew = false;
    },
    addExchangeRateRow() {
      if (this.exchangeRateRowCount < this.currencyList.length) {
        this.exchangeRateRow.push({
          exchangeRateEntryId: null,
          exchangeRateId: this.editedExchangeRate.exchangeRateId,
          currencyCode: null,
          currencyNameCode: null,
          buyRate: null,
          middleRate: null,
          sellRate: null,
          timestampOfCreating: null
        })
        this.exchangeRateRowCount++;
      }
    },
    getDate(value) {
      return dateFormatting.shortDate(dateFormatting.dateTimeParser(value))
    },
    deleteExchangeRateRow(index) {
      this.exchangeRateRow.splice(index, 1);
      this.exchangeRateRowCount--;
    },
    validForm() {
      return this.$refs.currencyRateForm.validate()
    },
    dateTimeParser(value) {
      const val = Object.create(value);
      if (!val) return ''
      if (typeof val === 'object') {
        if (val.length === 3) {
          return `${val.shift()}-${val.shift()}-${val.shift()}`
        } else {
          return `${val.shift()}-${val.shift()}-${val.shift()} ${val.shift()}:${val.shift()}:${val.shift()}`
        }
      }
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
    test() {
      console.log(7777777)
      console.log(this.exchangeRateRow)
      console.log(99999999)



      console.log(payload);

      console.log(2222);

      console.log(11111);

      console.log(this.exchangeRateRow);

      console.log(payload.exchangeRateEntryList);
      //console.log(this.getDate(this.editedExchangeRate.timestampOfEnd))

      //console.log(typeof this.editedExchangeRate.timestampOfEnd)
      //console.log(this.editedExchangeRate.timestampOfEnd)

      //console.log(this.getDate(this.editedExchangeRate.timestampOfEnd))

      //this.exchangeRateRowCount--;
      //console.log(this.exchangeRateRowCount)
      //console.log(this.currencyList.length)
    },
    async createExchangeRate() {

      if(this.validForm()) {

        const payload = {
          exchangeRate: {
            timestampOfStart: new Date(this.editedExchangeRate.timestampOfStart).toISOString(),
            timestampOfEnd: new Date(this.editedExchangeRate.timestampOfEnd).toISOString(),
            flActive: true
          },
          exchangeRateEntryList: this.exchangeRateRow,
        }

        console.log(payload);
        await fetch("api/exchangeRate/create", {
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
            this.closeCreateDialog();
            this.initialize();
          } else {
            this.pushSuccessNotification("The Exchange Rate was created");
            this.closeCreateDialog();
            this.initialize();
          }
        });
      }
    },
    async editExchangeRate() {

      if(this.validForm()) {

        const payload = {
          exchangeRate: {
            exchangeRateId: this.editedExchangeRate.exchangeRateId,
            timestampOfStart: new Date(this.editedExchangeRate.timestampOfStart).toISOString(),
            timestampOfEnd: new Date(this.editedExchangeRate.timestampOfEnd).toISOString(),
            flActive: true
          },
          exchangeRateEntryList: this.exchangeRateRow,
        }

        console.log(payload);
        await fetch("api/exchangeRate/edit", {
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
            this.closeCreateDialog();
            this.initialize();
          } else {
            this.pushSuccessNotification("The Exchange Rate was edited");
            this.closeCreateDialog();
            this.initialize();
          }
        });
      }
    },

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

.zoom1.v-select__selection,
.zoom1.v-select__selection--comma,
.zoom1.v-select input {

  font-size: 1.5em !important;
}

.zoom1.v-list-item__content,
.zoom1.v-list-item__title,
v-list.v-text-field input {

  font-size: 1.5em !important;
}

</style>

<style scoped>

.right-input >>> input {
  text-align: right;
}
</style>
