<script setup>
import { ref } from "vue";
import { HomeIcon,UserIcon,ChartPieIcon,CursorArrowRaysIcon } from "@heroicons/vue/24/outline";
import { useDark, useToggle } from "@vueuse/core";
const isDark = useDark();
const toggleDark = useToggle(isDark);
import axios from "axios";

let readings = ref([]);
let tab = ref(0);
let addInfo = ref(undefined)

const fetchInfo = async () => {
  await axios
    .get("http://localhost:8080/fetch-sensors-data")
    .then((res) => {
      console.log(res.data);
      readings.value = res && res.data && res.data.houses && res.data.houses[0];
      addInfo.value = readings.value['address']
      console.log("alen",addInfo)
    })
    .catch((err) => {
      console.log(err);
    });
};

fetchInfo();

const changeTab = (index) => {
  tab.value = index;
};
</script>
<template>
  <div class="m-4 text-slate-300">
    <div class="grid grid-cols-12 gap-6">
      <div class="col-span-3 p-3">
        <div
            @click="changeTab(0)"
          class="rounded-md border text-slate-300 hover:opacity-50 cursor-pointer p-3 mb-2 font-bold flex items-center gap-2"
        >
          <HomeIcon class="h-5 w-5" />
          Home Info
        </div>
        <div
            @click="changeTab(1)"
          class="rounded-md border text-slate-300 hover:opacity-50 cursor-pointer p-3 font-bold flex items-center gap-2 mb-2"
        >
        <UserIcon class="h-5 w-5" />

          User Info
        </div>
        <div
          class="rounded-md border text-slate-300 hover:opacity-50 cursor-pointer p-3 font-bold flex items-center gap-2 mb-2"
        >
        <ChartPieIcon class="h-5 w-5" />

          <div>Graph Visualization</div>
          <div class="text-sm border text-red-300 p-1 rounded">Coming Soon</div>
        </div>
        <div
          class="rounded-md border text-slate-300 hover:opacity-50 cursor-pointer p-3 font-bold flex items-center gap-2 mb-2"
        >
        <CursorArrowRaysIcon class="h-5 w-5" />
          <div>Control Center</div>
          <div class="text-sm border text-red-300 p-1 rounded">Coming Soon</div>
        </div>
      </div>
      <div class="col-span-9 border-l p-3">
        <div v-if="tab == 0" class="grid grid-cols-12 gap-4">
          <div
            v-for="(value, key) in readings"
            :key="key"
            :class="key == 'doors' || key == 'id' || key == 'address' || value.length <= 0 ? 'hidden' : ''"
            class="col-span-3 rounded-md p-3 border"
          >
            <div class="font-semibold text-xl border-b-2 mb-3 capitalize">{{ key }}</div>
            <div class="grid grid-cols-12 gap-3">
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/gas.svg" alt="gas" class="h-5 w-5" />
                <div>
                  {{
                    (value &&
                      value[0] &&
                      value[0].gasSensor &&
                      value[0].gasSensor.values[0].toFixed(2)) ||
                    "--"
                  }}
                  cf
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/humidity.svg" alt="humidity" class="h-5 w-5" />
                <div>
                  {{
                    (value &&
                      value[0] &&
                      value[0].humiditySensor &&
                      value[0].humiditySensor.values[0].toFixed(2)) ||
                    "--"
                  }}
                  RH
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/light.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    (value &&
                      value[0] &&
                      value[0].lightSensor &&
                      value[0].lightSensor.values[0].toFixed(2)) ||
                    "--"
                  }}
                  lx
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/temprature.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    (value &&
                      value[0] &&
                      value[0].temperatureSensor &&
                      value[0].temperatureSensor.values[0].toFixed(2)) ||
                    "--"
                  }}
                  °C
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/ventilation.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    (value &&
                      value[0] &&
                      value[0].ventilationSensor &&
                      value[0].ventilationSensor.values[0].toFixed(2)) ||
                    "--"
                  }}
                  m<sup>3</sup>/s
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/water.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    value &&
                    value[0] &&
                    value[0].waterLeakSensor &&
                    value[0].waterLeakSensor.leakingWater
                      ? "Leaking"
                      : "No leakage"
                  }}
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/door.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    value &&
                    value[0] &&
                    value[0].doors &&
                    value[0].doors[0] &&
                    value[0].doors[0].doorSensor &&
                    value[0].doors[0].doorSensor.doorOpen
                      ? "Open"
                      : "Closed"
                  }}
                </div>
              </div>
              <div class="flex items-center gap-2 col-span-6 text-xl font-medium">
                <img src="/window.svg" alt="" class="h-5 w-5" />
                <div>
                  {{
                    value &&
                    value[0] &&
                    value[0].windows &&
                    value[0].windows[0] &&
                    value[0].windows[0].windowSensor &&
                    value[0].windows[0].windowSensor.windowOpen
                      ? "Open"
                      : "Closed"
                  }}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="tab == 1">
            <div class="text-2xl font-bold">Address</div>
            <div class="grid grid-cols-12 gap-4">
                <div class="col-span-6">
                    <div class="font-semibold text-xl border-b-2 mb-3 capitalize">Street Number</div>
                    <div class="text-xl font-medium">{{ addInfo && addInfo.streetNumber }}</div>
                </div>
                <div class="col-span-6">
                    <div class="font-semibold text-xl border-b-2 mb-3 capitalize">City</div>
                    <div class="text-xl font-medium">{{ addInfo && addInfo.city }}</div>
                </div>
                <div class="col-span-6">
                    <div class="font-semibold text-xl border-b-2 mb-3 capitalize">Country</div>
                    <div class="text-xl font-medium">{{ addInfo && addInfo.country }}</div>
                </div>
                <div class="col-span-6">
                    <div class="font-semibold text-xl border-b-2 mb-3 capitalize">Post Code</div>
                    <div class="text-xl font-medium">{{ addInfo && addInfo.postcode }}</div>
                </div>
                </div>
        </div>
      </div>
    </div>
  </div>
</template>
<route lang="yaml">
meta:
  layout: app
</route>
