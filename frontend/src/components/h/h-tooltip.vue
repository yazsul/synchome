<script setup>
const props = defineProps({
  class: String,
  pointerClass: String,
  position: {
    type: String,
    default: 'top',
    validator(value) {
      return ['top', 'left', 'bottom', 'right'].includes(value)
    }
  }
})

console.log("this is props passed", props.class, props.pointerClass);
</script>

<template>
  <div class="relative flex group">
    <div>
      <slot name="hovered-item"></slot>
    </div>
    <div
      class="z-50 drop-shadow-3xl absolute w-48 h-32 hidden group-hover:block rounded-xl  dark:bg-secondary font-normal "
      :class="[{
        '-translate-y-[20%]   inset-x-1/2 -translate-x-1/2  bottom-[100%]': props.position === 'top',
        '-translate-x-[20%]   inset-y-1/2 -translate-y-1/2  right-[100%]': props.position === 'left',
        'translate-x-[20%]   inset-y-1/2 -translate-y-1/2  left-[100%]': props.position === 'right',
        'translate-y-[20%]   inset-x-1/2 -translate-x-1/2  top-[100%]': props.position === 'bottom',
      }, props.class ? props.class : 'bg-primary']">
      <span class="absolute border border-transparent" :class="[
        {
          'left-[50%] top-[100%] -translate-x-[50%] border-b-[36px] border-x-[30px] rotate-180 dark:border-b-secondary': props.position === 'top',
          'inset-y-1/2 -translate-y-1/2 left-[100%] border-l-[30px] border-y-[30px] rotate-0 dark:border-l-secondary': props.position === 'left',
          'inset-x-1/2 -translate-x-1/2 bottom-[100%] border-b-[30px] border-x-[30px] dark:border-b-secondary': props.position == 'bottom',
          'inset-y-1/2 -translate-y-1/2 right-[100%] border-r-[30px] border-y-[30px] dark:border-r-secondary': props.position == 'right'
        },
        props.pointerClass ? props.pointerClass : ''
      ]"></span>
      <!-- <span class="absolute left-[50%] border-4 border-red-400 -translate-x-1/2 ">what</span> -->
      <slot>
      </slot>
    </div>

  </div>

</template>

<!-- <style>
.tooltip {
  display: inline-block;
  position: relative;
  border-bottom: 1px dotted #666;
  text-align: left;
}

.tooltip h3 {
  margin: 12px 0;
}

.tooltip .top {
  min-width: 200px;
  max-width: 400px;
  top: -20px;
  left: 50%;
  transform: translate(-30%, -100%);
  padding: 10px 20px;
  color: #ffffff;
  background-color: #009cdc;
  font-weight: normal;
  font-size: 14px;
  border-radius: 8px;
  position: absolute;
  z-index: 99999999;
  box-sizing: border-box;
  box-shadow: 0 1px 8px rgba(0, 0, 0, 0.5);
  display: none;
}

.tooltip:hover .top {
  display: block;
}

.tooltip .top i {
  position: absolute;
  top: 100%;
  left: 30%;
  margin-left: -15px;
  width: 30px;
  height: 15px;
  overflow: hidden;
}

.tooltip .top i::after {
  content: '';
  position: absolute;
  width: 15px;
  height: 15px;
  left: 50%;
  transform: translate(-50%, -50%) rotate(45deg);
  background-color: #009cdc;
  box-shadow: 0 1px 8px rgba(0, 0, 0, 0.5);
}
</style> -->