<script setup>
import { Editor, EditorContent } from "@tiptap/vue-3";
import StarterKit from "@tiptap/starter-kit";
import Document from "@tiptap/extension-document";
import Heading from "@tiptap/extension-heading";
import Paragraph from "@tiptap/extension-paragraph";
import Text from "@tiptap/extension-text";
import { ref, watch, computed, onBeforeUnmount } from "vue";
import FormatBold from "@/assets/format-bold.svg?component";
import FormatItalic from "@/assets/format-italic.svg?component";
import ListBullet from "@/assets/list-bullet.svg?component";
import NumberedList from "@/assets/numbered-list.svg?component";
import Undo from "@/assets/undo.svg?component";
import Redo from "@/assets/redo.svg?component";
import Strikethrough from "@/assets/strikethrough.svg?component";
import { useField } from "vee-validate";
import { useDark, useToggle } from "@vueuse/core";
const isDark = useDark({});

const emit = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: {
    type: String,
    default: "",
  },
  height: {
    type: String,
    default: "h-24",
  },
  name: {
    type: String,
    default: undefined,
    required: true,
  },
  disabled: Boolean,
  rules: String,
});
const { errorMessage, value: inputValue } = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

const match = (haystack, niddle) => {
  const res = haystack.match(niddle);
  return (res && res[0] && true) || false;
};

const editor = new Editor({
  editorProps: {
    attributes: {
      class: `  prose prose-sm sm:prose lg:prose-lg xl:prose-2xl mx-auto ${props.height} focus:outline-none p-3`,
    },
  },
  content: inputValue.value,
  extensions: [
    StarterKit,
    Document,
    Paragraph,
    Text,
    Heading.configure({
      levels: [1, 2, 3],
    }),
  ],
  onUpdate: () => {
    const result = match(editor.getHTML(), /^<p>[ \n]*<\/p>$/);
    inputValue.value = result ? "" : editor.getHTML();
    emit("update:modelValue", editor.getHTML());
  },
});

watch(
  () => props.modelValue,
  (value) => {
    //
    const isSame = editor.getHTML() === value;

    if (isSame) {
      return;
    }

    editor.commands.setContent(value, false);
  }
);

onBeforeUnmount(() => {
  editor.destroy();
});
</script>

<template>
  <div
    class="border-2 rounded-xl"
    :class="{
      'focus:ring-primary  focus:border-primary hover:border-primary-2 border-gray-300 border-1':
        !errorMessage,
      'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500':
        errorMessage,
    }"
  >
    <div v-if="editor" class="border-b-2">
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1 font-bold"
        @click="editor.chain().focus().toggleBold().run()"
        :class="{ 'is-active': editor.isActive('bold') }"
      >
        <FormatBold class="h-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1 text-center"
        @click="editor.chain().focus().toggleItalic().run()"
        :class="{ 'is-active': editor.isActive('italic') }"
      >
        <FormatItalic class="h-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1 h-12 w-12 line-through"
        @click="editor.chain().focus().toggleStrike().run()"
        :class="{ 'is-active': editor.isActive('strike') }"
      >
        <Strikethrough class="h-4 w-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>

      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().setParagraph().run()"
        :class="{ 'is-active': editor.isActive('paragraph') }"
      >
        <div class="h-4 w-4 text-black dark:text-secondary-8 font-bold">P</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 1 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 1 }) }"
      >
        <div class="h-4 w-4 text-black dark:text-secondary-8 font-bold">H1</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 2 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }"
      >
        <div class="h-4 w-4 text-black dark:text-secondary-8 font-bold">H2</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 3 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 3 }) }"
      >
        <div class="h-4 w-4 text-black dark:text-secondary-8 font-bold">H3</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 4 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 4 }) }"
      >
        <div class="h-4 w-4 text-black dark:text-secondary-8 font-bold">H4</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 5 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 5 }) }"
      >
        <div class="h-4 w-4 text-black font-bold dark:text-secondary-8">H5</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleHeading({ level: 6 }).run()"
        :class="{ 'is-active': editor.isActive('heading', { level: 6 }) }"
      >
        <div class="h-4 w-4 text-black font-bold dark:text-secondary-8">H6</div>
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleBulletList().run()"
        :class="{ 'is-active': editor.isActive('bulletList') }"
      >
        <ListBullet class="h-4 w-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().toggleOrderedList().run()"
        :class="{ 'is-active': editor.isActive('orderedList') }"
      >
        <NumberedList class="h-4 w-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().undo().run()"
      >
        <Undo class="w-4 h-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
      <button
        type="button"
        class="p-2 mx-1 rounded-lg border-1"
        @click="editor.chain().focus().redo().run()"
      >
        <Redo class="w-4 h-4" :class="isDark ? 'icon_dark ' : ''" />
      </button>
    </div>
    <editor-content class="overflow-auto" :editor="editor" />
  </div>
</template>

<style scoped>
.icon_dark {
  filter: invert(100%) sepia(96%) saturate(0%) hue-rotate(46deg)
    brightness(105%) contrast(103%);
}
</style>
