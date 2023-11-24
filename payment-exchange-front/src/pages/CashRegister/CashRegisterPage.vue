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
    <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
      <h1 class="page-title"> CashRegister List</h1>

      <v-menu offset-y>
        <template v-slot:activator="{on, attrs}">
          <v-dialog
            v-model="dialogCashRegister"
            max-width="800px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="mx-2"
                dark
                rounded
                x-large
                color="primary"
                v-bind="attrs"
                v-on="on"
              >
                <v-icon dark>
                  mdi-plus
                </v-icon>
                New Cash Register
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5 center">{{ formTitle }}</span>
              </v-card-title>
              <v-divider/>
              <v-card-text class="pa-6 pt-5 mb-1">
                <v-form
                  ref="createCashRegisterForm"
                  v-model="validCashRegisterForm"
                  lazy-validation>
                  <v-layout row justify-space-around >

                    <v-col cols="12" sm="2" md="4">
                      <v-text-field
                        v-model="editCashRegister.cashRegisterId"
                        label="Cash Register Id"
                        outlined
                        disabled
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                  </v-layout>
                  <v-layout row justify-space-around>
                    <v-col cols="12" sm="6" md="5">
                      <v-text-field
                        v-model="editCashRegister.name"
                        label="CashRegister Name"
                        :rules="[rules.required]"
                        prepend-inner-icon="mdi-account"
                        outlined
                        class="zoom1"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="5">
                      <v-select
                        label="Type"
                        class="form-control zoom1"
                        :items="cashRegisterType"
                        item-value="value"
                        item-text="name"
                        v-model="editCashRegister.flTreasury"
                        :disabled="editCashRegister.flInUse"
                        outlined
                        required
                      ></v-select>
                    </v-col>
                  </v-layout>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="primary"
                  x-large
                  @click="close"
                >
                  Cancel
                </v-btn>
                <v-btn
                  color="primary"
                  x-large
                  v-show="editCase"
                  @click="editExistCashRegister"
                >
                  Edit
                </v-btn>
                <v-btn
                  color="primary"
                  x-large
                  v-show="!editCase"
                  @click="createNewCashRegister"
                >
                  Create
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5">
                <v-row justify="center" align="center">
                  <h5 class="text-h5">Are you sure you want to delete this item?</h5>
                </v-row>
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" x-large text @click="closeDelete">Cancel</v-btn>
                <v-btn color="blue darken-1" x-large text @click="deleteItemConfirm">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogInUse" max-width="550px" min-height="500">
            <v-card>
              <v-card-title class="text-h5">
                <v-row justify="center" align="center">
                  <h5 class="text-h5">You can't delete cashRegister witch in-use!</h5>
                </v-row>
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" x-large text @click="closeInUseDialog">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>
      </v-menu>
    </v-row>

    <v-card>
      <v-data-table
        :headers="headers"
        :items="cashRegisters"
        sort-by="userId"
        :single-expand="singleExpand"
        :expanded.sync="expanded"
        item-key="cashRegister.cashRegisterId"
        show-expand
      >
        <template v-slot:item.cashRegister.timestampOfCreation="{ item }">
          {{ convertDateTime(item.cashRegister.timestampOfCreation) }}
        </template>
        <template v-slot:item.cashRegister.flTreasury="{ item }">
          {{ (item.cashRegister.flTreasury)? "Treasury" : "CashRegister" }}
        </template>
        <template v-slot:item.cashRegister.flInUse="{ item }">
          <v-chip
            :color="getFlActiveColor(item.cashRegister.flInUse)"
            dark
          >
            {{ item.cashRegister.flInUse }}
          </v-chip>
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
        <template v-slot:expanded-item="{ headers, item }" >
          <td :colspan="headers.length" v-if="item.workPlace.workPlaceId != null">
            <v-row no-gutters>
              <v-col cols="12" sm="3">
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Workplace Id: <b>{{ item.workPlace.workPlaceId }}</b></v-card>
              </v-col>

              <v-col cols="12" sm="3">
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >User Id: <b>{{ item.workPlace.userId }}</b></v-card>
              </v-col>

              <v-col cols="12" sm="6">
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Datetime of open: <b>{{ item.workPlace.timestampOfOpen }}</b></v-card>
              </v-col>

            </v-row>
          </td>
          <td :colspan="headers.length" v-else>
            <v-row no-gutters>
              <v-col cols="12" sm="12">
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >Cash register is not In Use</v-card>
              </v-col>
            </v-row>
          </td>
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
  </v-container>

</template>

<script>

export default {
  name: "CashRegisterPage",
  data() {
    return {
      overlay: false,
      dialogCashRegister: false,
      dialogDelete: false,
      dialogInUse: false,
      editedIndex: -1,
      editCase:false,
      validCashRegisterForm: true,
      rules: {
        required: value => !!value || 'Required field',
        counter: value => value.length <= 20 || 'Max 20 characters',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }
      },
      headers: [
        {text: 'Id', value: 'cashRegister.cashRegisterId'},
        {text: 'Cash Register Name', value: 'cashRegister.name'},
        {text: 'Creation timestamp', value: 'cashRegister.timestampOfCreation'},
        //{text: 'FlActive', value: 'flActive', class: "title"},
        {text: 'Type', value: 'cashRegister.flTreasury'},
        {text: 'In Use', value: 'cashRegister.flInUse', sortable: false},
        {text: 'Actions', value: 'actions', sortable: false},
        {text: ' ', value: 'data-table-expand'},
      ],
      cashRegisters: [{
        cashRegister: {
          cashRegisterId: null,
          name: null,
          flActive: null,
          timestampOfCreation: null,
          flInUse: null,
          flTreasury: null,
        },
        workPlace: {
          workPlaceId: null,
          cashRegisterId: null,
          userId: null,
          timestampOfOpen: null,
          timestampOfClose: null,
          timestampOfCreation: null,
        }
      }],
      editCashRegister: {
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
        flTreasury:false,
      },
      deleteCashRegister: {
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
      },
      emptyCashRegister: {
        cashRegisterId: null,
        name: null,
        flActive: null,
        timestampOfCreation: null,
        flInUse: null,
        flTreasury:false,
      },
      cashRegisterType:[
        {value:false, name: "Cash Register"},
        {value:true, name: "Treasury"}
        ],
      expanded: [],
      singleExpand: true,
    }
  },

  computed: {
    formTitle() {
      this.editCase ? this.dialogCashRegister = true : this.dialogCashRegister = false
      return this.editCase ? 'Edit Cash Register' : 'New Cash Register'
    },
  },
  watch: {
    dialogCashRegister(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  created() {
    this.initialize()
  },
  mounted() {
    this.onLoad()
  },
  methods: {

    async onLoad() {
      this.overlay = true;

      this.cashRegisters = await fetch('/api/cashRegister/getAllCashRegisters', {
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
    initialize() {
      this.onLoad();
    },
    convertDateTime(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2)  + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear() + " " +
        ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
    },
    convertDate(item){
      const date = new Date(item);
      return ("0" + (date.getDate())).slice(-2) + "." + ("0" + (date.getMonth()+1)).slice(-2) + "." + date.getFullYear();
    },
    editItem(item) {
      this.editCase = true;
      this.editedIndex = this.cashRegisters.indexOf(item)
      this.editCashRegister = Object.assign({}, item.cashRegister)
      this.dialogCashRegister = true
    },

    deleteItem(item) {
      if(item.cashRegister.flInUse) {
        this.dialogInUse = true
      }else{
      this.editedIndex = this.cashRegisters.indexOf(item)
      this.deleteCashRegister = Object.assign({}, item.cashRegister)
        this.dialogDelete = true
      }
    },
    close() {
      this.dialogCashRegister = false
      this.editCase = false;
      this.$refs.createCashRegisterForm.reset()
      this.$nextTick(() => {
        this.editedIndex = -1
        this.editCashRegister = Object.assign({}, this.emptyCashRegister)
      })
    },
    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedIndex = -1
        this.editCashRegister = Object.assign({}, this.emptyCashRegister)
      })
    },
    closeInUseDialog(){
      this.dialogInUse = false;
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
    createNewCashRegister() {
      //if (this.validForm()) {
      const payload = {
        name: this.editCashRegister.name,
        flActive: true,
        flInUse: false,
        flTreasury: this.editCashRegister.flTreasury,
        timestampOfCreation: new Date().toISOString(),
      }

      console.log(payload);

      const data = new FormData();
      //data.append( "json", JSON.stringify( payload ) );

      fetch("api/cashRegister/create", {
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
          this.close();
          this.initialize();
          throw new Error('Something went wrong');
        } else {
          this.pushSuccessNotification("The CashRegister was created");
          this.close();
          this.initialize();
        }
      });
      //}
    },
    editExistCashRegister() {
      //if (this.validForm()) {
      const payload = {
        cashRegisterId: this.editCashRegister.cashRegisterId,
        name: this.editCashRegister.name,
        flActive: true,
        flInUse: false,
        flTreasury: this.editCashRegister.flTreasury,
        timestampOfCreation: new Date().toISOString(),
      }

      console.log(payload);

      const data = new FormData();
      //data.append( "json", JSON.stringify( payload ) );

      fetch("api/cashRegister/edit", {
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
          this.close();
          this.initialize();
          throw new Error('Something went wrong');
        } else {
          this.pushSuccessNotification("The CashRegister was created");
          this.close();
          this.initialize();
        }
      });
      //}
    },
    async deleteItemConfirm() {

      await fetch('/api/cashRegister/deleteCashRegister/' + this.deleteCashRegister.cashRegisterId, {
        method: 'DELETE',
        headers: {
          Authorization: sessionStorage.getItem('user-token'),
          Accept: 'application/json',
        }
      }).then(async (response) => {
        if (!response.ok) {
          this.errorResp = await response.json();
          this.pushErrorNotification(this.errorResp.errorMessage);
          this.closeDelete();
          this.initialize();
          throw new Error('Something went wrong');
        } else {
          this.pushSuccessNotification("The Cash register was deleted");
          this.closeDelete();
          this.initialize();
          this.cashRegisters.splice(this.deleteIndex, 1)
        }
      });

      this.closeDelete();


    },/*
    rowClick: function (item, row) {
      let selectState = (row.isSelected) ? false : true;
      row.select(selectState);
    },*/
    test() {

    },/*
    markSelectedItem: function (item) {
      return item.cashRegisterId == this.selectedId ? 'style-1' : null
    },*/
    getFlActiveColor(flActive) {
      if (!flActive) return 'red'
      else return 'green'
    },
  }
}
</script>

<style>

.theme--light.v-data-table tbody tr.v-data-table__selected {
  background: #f5c17d70 !important;
}

.theme--dark.v-data-table tbody tr.v-data-table__selected {
  background: #a17b4970 !important;
}

.theme--dark.v-data-table tbody tr.v-data-table__selected:hover {
  background: #a17b49c2 !important;
}

.theme--light.v-data-table tbody tr.v-data-table__selected:hover {
  background: #ffd296d2 !important;
}

.v-data-footer{
  font-size: 1.5em !important;

}

.compact-form {
  transform: scale(1.3);
  transform-origin: left;
}
</style>
