<template>
  <modal v-model="openModal" class="mx-auto font-body h-screen w-1/4">
    <div class="py-6 px-12 bg-pp-blue border">
      <div class="flex justify-between items-center my-3">
        <div class="font-bold text-xl p-1 text-white">Room Details</div>
        <div class="hover:cursor-pointer pr-5" @click="openModal = false">
          <div class="bg-gray-300 rounded-md h-8 w-8 hover:bg-opacity-30">
            <XMarkIcon class="w-8 h-8 p-2" />
          </div>
        </div>
      </div>
      <div class="h-0.5 bg-gray-200 my-2"></div>
      <div class="mb-20">
        <div class="text-white text-md mb-3">Doors</div>
        <HListNew
        name="Door"
        v-model="helper.SDoor"
        v-model:selected="helper.door_id"
        :items="materialList"
        @search="selectableVsearch"
        :trailingIcon="ChevronDownIcon"
        hideDetail
      >
        <template v-slot:label>
          <p class="text-gray-400 text-sm">Material<span class="red-color">*</span></p>
        </template>
      </HListNew>
      <div class="text-white text-md mb-3 mt-6">Windows</div>
        <HListNew
        name="Door"
        v-model="helper.sWindow"
        v-model:selected="helper.window_id"
        :items="materialList"
        @search="selectableVsearch"
        :trailingIcon="ChevronDownIcon"
        hideDetail
      >
        <template v-slot:label>
          <p class="text-gray-400 text-sm">Material<span class="red-color">*</span></p>
        </template>
      </HListNew>
      </div>
    </div>
  </modal>

  <div class="grid grid-cols-12 gap-4 p-4">
    <div class="col-span-9 h-full">
      <h3 class="text-lg font-bold">Design Your Home</h3>
      <div class="border border-dashed h-full">
        <grid-layout
          :layout.sync="list2"
          :col-num="12"
          :row-height="30"
          :is-draggable="draggable"
          :is-resizable="resizable"
          :vertical-compact="true"
          :use-css-transforms="true"
        >
          <grid-item
            v-for="item in list2"
            :static="item.static"
            :x="item.x"
            :y="item.y"
            :w="item.w"
            :h="item.h"
            :i="item.i"
            @click="handleClick"
          >
            <div class="group">
              <div class="flex justify-end p-2">
                <PencilSquareIcon
                  class="text-black h-4 w-4 hidden cursor-pointer group-hover:block"
                ></PencilSquareIcon>
              </div>
              <div class="text flex justify-center">
                <div class="flex gap-2 items-center text-center">
                  <img v-if="item.icon" :src="item.icon" class="w-8 h-8" />
                  {{ itemTitle(item) }}
                </div>
              </div>
            </div>
          </grid-item>
        </grid-layout>
      </div>
    </div>

    <!-- Draggable 1 -->
    <div class="col-span-3">
      <div>
        <h3 class="text-lg font-bold">Home Components</h3>
        <draggable
          class="p-4 bg-pp-blue border shadow"
          :list="list1"
          :group="{ name: 'people', pull: 'clone', put: false }"
          :clone="cloneComponent"
          @change="log"
          item-key="id"
        >
          <template #item="{ element }">
            <div class="p-2 mb-2 bg-white rounded shadow flex items-center gap-2">
              <img v-if="element.icon" :src="element.icon" class="w-8 h-8" />
              {{ element.name }}
            </div>
          </template>
        </draggable>
      </div>

      <!-- Draggable 2 -->
      <div class="mt-4">
        <h3 class="text-lg font-bold">Your home components</h3>
        <draggable
          class="p-4 bg-pp-blue border border-dashed shadow"
          :list="list2"
          group="people"
          @change="log"
          item-key="id"
        >
          <template #item="{ element }">
            <div
              class="group p-2 mb-2 bg-white rounded shadow flex items-center justify-between gap-2"
            >
              <div class="flex items-center">
                <img v-if="element.icon" :src="element.icon" class="w-8 h-8" />
                {{ element.name }}
              </div>

              <XCircleIcon
                @click="removeComponent(element)"
                class="text-red-400 h-6 w-6 hidden cursor-pointer group-hover:block"
              ></XCircleIcon>
            </div>
          </template>
        </draggable>
      </div>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import { GridLayout, GridItem } from "vue3-grid-layout-next";
import { XCircleIcon, PencilSquareIcon, XMarkIcon } from "@heroicons/vue/24/solid";
import { ChevronDownIcon } from "@heroicons/vue/24/outline";

import modal from "@/components/h/modal.vue";
// import HListNew from "@/components/h/h-text-field.vue"
// import HListNew from "@/components/h/h-list-new.vue";
import HListNew from "@/components/h/h-list-new.vue";
let idGlobal = 0;
export default {
  name: "custom-clone",
  display: "Custom Clone",
  order: 3,
  components: {
    draggable,
    GridLayout,
    GridItem,
    XCircleIcon,
    PencilSquareIcon,
    modal,
    XMarkIcon,
    HListNew,
  },
  data() {
    return {
      list1: [
        { icon: "/room.png", name: "Room", id: 1 },
        { icon: "/garden.png", name: "Garden", id: 2 },
        { icon: "/livingroom.png", name: "Living Room", id: 3 },
        { icon: "/kitchen.png", name: "Kitchen", id: 4 },
        { icon: "/bathroom.png", name: "Bath Room", id: 5 },
        { icon: "/garage.png", name: "Garage", id: 6 },
      ],
      list2: [],
      draggable: true,
      resizable: true,
      index: 0,
      openModal: false,
      clickTimer: null,
      searchTxt: "",
      helper: {},
      ChevronDownIcon
    };
  },
  computed: {
    materialList(){
      let lists = [
            { id: 'wood', name: 'Wood' },
            { id: 'steel', name: 'Steel' },
            { id: 'aluminum', name: 'Aluminum' },
            { id: 'glass', name: 'Glass' },
          ];
      return this.searchTxt ? lists.filter(itm => {
        return itm.name.toLowerCase().includes(this.searchTxt.toLowerCase());
      }) : lists;
    },
},
  methods: {
    selectableVsearch(f) {
      this.searchTxt = f;
    },
    itemTitle(item) {
      let result = item.name;
      if (item.static) {
        result += " - Static";
      }
      return result;
    },
    log: function (evt) {
      window.console.log(evt);
    },
    cloneComponent(itm) {
      console.log("list2", this.list2);
      let idd = idGlobal++;
      return {
        id: idd,
        i: idd,
        x: 0,
        y: 0,
        w: 2,
        h: 2,
        icon: itm.icon,
        name: `${itm.name}-${idd}`,
      };
    },
    removeComponent(itm) {
      this.list2 = this.list2.filter((item) => item.id !== itm.id);
    },
    handleClick() {
      if (this.clickTimer) {
        clearTimeout(this.clickTimer);
        this.clickTimer = null;
        this.onDoubleClick();
        return;
      }
      this.clickTimer = setTimeout(() => {
        this.clickTimer = null;
      }, 250);
    },
    onDoubleClick() {
      this.openModal = true;
    },
  },
};
</script>
<style scoped>
.vue-grid-layout {
  background: #07211d;
}

.vue-grid-item:not(.vue-grid-placeholder) {
  background: #ccc;
  border: 1px solid black;
}

.vue-grid-item .resizing {
  opacity: 0.9;
}

.vue-grid-item .static {
  background: #cce;
}

.vue-grid-item .text {
  font-size: 24px;
  text-align: center;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  margin: auto;
  height: 100%;
  width: 100%;
}

.vue-grid-item .no-drag {
  height: 100%;
  width: 100%;
}

.vue-grid-item .minMax {
  font-size: 12px;
}

.vue-grid-item .add {
  cursor: pointer;
}

.vue-draggable-handle {
  position: absolute;
  width: 20px;
  height: 20px;
  top: 0;
  left: 0;
  background: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='10' height='10'><circle cx='5' cy='5' r='5' fill='#999999'/></svg>")
    no-repeat;
  background-position: bottom right;
  padding: 0 8px 8px 0;
  background-repeat: no-repeat;
  background-origin: content-box;
  box-sizing: border-box;
  cursor: pointer;
}
</style>
<route lang="yaml">
meta:
  layout: app
</route>
