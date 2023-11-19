<template>
  <div class="flex flex-wrap">
  <!-- Draggable 1 -->
  <div class="w-1/4">
    <h3 class="text-lg font-bold">Draggable 1</h3>
    <draggable
      class="p-4 bg-gray-100 rounded-lg shadow"
      :list="list1"
      :group="{ name: 'people', pull: 'clone', put: false }"
      :clone="cloneDog"
      @change="log"
      item-key="id"
    >
      <template #item="{ element }">
        <div class="p-2 mb-2 bg-white rounded shadow">
          {{ element.name }}
        </div>
      </template>
    </draggable>
  </div>

  <!-- Draggable 2 -->
  <div class="w-1/4">
    <h3 class="text-lg font-bold">Draggable 2</h3>
    <draggable
      class="p-4 bg-gray-100 rounded-lg shadow"
      :list="list2"
      group="people"
      @change="log"
      item-key="id"
    >
      <template #item="{ element }">
        <div class="p-2 mb-2 bg-white rounded shadow">
          {{ element.name }}
        </div>
      </template>
    </draggable>
  </div>

  <!-- Raw Displayer for List 1 -->
  <div class="w-1/4">
    <rawDisplayer class="p-4 bg-gray-100 rounded-lg shadow" :value="list1" title="List 1" />
  </div>

  <!-- Raw Displayer for List 2 -->
  <div class="w-1/4">
    <rawDisplayer class="p-4 bg-gray-100 rounded-lg shadow" :value="list2" title="List 2" />
  </div>
</div>

<div class="p-10">
    <grid-layout :layout.sync="list2"
                 :col-num="12"
                 :row-height="30"
                 :is-draggable="draggable"
                 :is-resizable="resizable"
                 :vertical-compact="true"
                 :use-css-transforms="true"
    >
        <grid-item v-for="item in list2"
                   :static="item.static"
                   :x="item.x"
                   :y="item.y"
                   :w="item.w"
                   :h="item.h"
                   :i="item.i"
        >
            <span class="text">{{itemTitle(item)}}</span>
        </grid-item>
    </grid-layout>
</div>
<div class="parent">
    <div class="text top">Top Text</div>
    <div class="text right">Right Text</div>
    <div class="text bottom">Bottom Text</div>
    <div class="text left">Left Text</div>
    <div class="center-div">
        Center Div
    </div>
</div>

</template>
  
  <script>
  import draggable from "vuedraggable";
  import { GridLayout, GridItem } from "vue3-grid-layout-next"
  let idGlobal = 8;
  export default {
    name: "custom-clone",
    display: "Custom Clone",
    order: 3,
    components: {
      draggable,
      GridLayout,
        GridItem
    },
    data() {
      return {
        list1: [
          { name: "dog 1", id: 1 },
          { name: "dog 2", id: 2 },
          { name: "dog 3", id: 3 },
          { name: "dog 4", id: 4 }
        ],
        list2: [
          { name: "cat 5", id: 5,"x":0,"y":0,"w":2,"h":2,"i":"5", static: false },
          { name: "cat 6", id: 6 ,"x":0,"y":0,"w":2,"h":2,"i":"6", static: false},
          { name: "cat 7", id: 7 ,"x":2,"y":0,"w":2,"h":4,"i":"7", static: true}
        ],
        layout: [
                {"x":0,"y":0,"w":2,"h":2,"i":"0", static: false},
                {"x":2,"y":0,"w":2,"h":4,"i":"1", static: true},
                {"x":4,"y":0,"w":2,"h":5,"i":"2", static: false},
                {"x":6,"y":0,"w":2,"h":3,"i":"3", static: false},
                {"x":8,"y":0,"w":2,"h":3,"i":"4", static: false},
                {"x":10,"y":0,"w":2,"h":3,"i":"5", static: false},
                {"x":0,"y":5,"w":2,"h":5,"i":"6", static: false},
                {"x":2,"y":5,"w":2,"h":5,"i":"7", static: false},
                {"x":4,"y":5,"w":2,"h":5,"i":"8", static: false},
                {"x":6,"y":3,"w":2,"h":4,"i":"9", static: true},
                {"x":8,"y":4,"w":2,"h":4,"i":"10", static: false},
                {"x":10,"y":4,"w":2,"h":4,"i":"11", static: false},
                {"x":0,"y":10,"w":2,"h":5,"i":"12", static: false},
                {"x":2,"y":10,"w":2,"h":5,"i":"13", static: false},
                {"x":4,"y":8,"w":2,"h":4,"i":"14", static: false},
                {"x":6,"y":8,"w":2,"h":4,"i":"15", static: false},
                {"x":8,"y":10,"w":2,"h":5,"i":"16", static: false},
                {"x":10,"y":4,"w":2,"h":2,"i":"17", static: false},
                {"x":0,"y":9,"w":2,"h":3,"i":"18", static: false},
                {"x":2,"y":6,"w":2,"h":2,"i":"19", static: false}
            ],
            draggable: true,
            resizable: true,
            index: 0
      };
    },
    methods: {
     itemTitle(item) {
            let result = item.i;
            if (item.static) {
                result += " - Static";
            }
            return result;
      },
      log: function(evt) {
        window.console.log(evt);
      },
      cloneDog({ id }) {
        return {
          id: idGlobal++,
          i: idGlobal++,
          x:0,y:0,w:2,h:2,
          name: `cat ${id}`
        };
      }
    }
  };
  </script>
  <style scoped>
  .parent {
    position: relative;
    width: 300px; /* or any size you prefer */
    height: 300px; /* or any size you prefer */
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid black; /* optional, just for visualization */
}

.text {
    position: absolute;
    color: white; /* text color */
}

.text::before {
    content: '';
    position: absolute;
    background-color: black; /* color of the line */
}

.top {
    top: 0;
    left: 50%;
    transform: translate(-50%, -50%);
}

.top::before {
    width: 2px; /* line width */
    height: calc(50% - 50px); /* half parent height minus half center div height */
    bottom: 100%;
    left: 50%;
    transform: translateX(-50%);
}

.right {
    right: 0;
    top: 50%;
    transform: translate(50%, -50%);
}

.right::before {
    height: 2px; /* line width */
    width: calc(50% - 50px); /* half parent width minus half center div width */
    left: 100%;
    top: 50%;
    transform: translateY(-50%);
}

.bottom {
    bottom: 0;
    left: 50%;
    transform: translate(-50%, 50%);
}

.bottom::before {
    width: 2px; /* line width */
    height: calc(50% - 50px); /* half parent height minus half center div height */
    top: 100%;
    left: 50%;
    transform: translateX(-50%);
}

.left {
    left: 0;
    top: 50%;
    transform: translate(-50%, -50%);
}

.left::before {
    height: 2px; /* line width */
    width: calc(50% - 50px); /* half parent width minus half center div width */
    right: 100%;
    top: 50%;
    transform: translateY(-50%);
}

.center-div {
    width: 100px; /* or any size you prefer */
    height: 100px; /* or any size you prefer */
    background-color: #333; /* or any color you prefer */
    display: flex;
    justify-content: center;
    align-items: center;
    color: white; /* text color */
    position: relative; /* to stack on top of lines */
}

  .vue-grid-layout {
      background: #eee;
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
      background: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='10' height='10'><circle cx='5' cy='5' r='5' fill='#999999'/></svg>") no-repeat;
      background-position: bottom right;
      padding: 0 8px 8px 0;
      background-repeat: no-repeat;
      background-origin: content-box;
      box-sizing: border-box;
      cursor: pointer;
  }
  
  
  </style>