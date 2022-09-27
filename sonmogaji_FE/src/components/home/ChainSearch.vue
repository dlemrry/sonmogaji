<template>
  <div id="chain-search">
    <div ref="slider" class="keen-slider d-flex" style="overflow: hidden">
      <div class="keen-slider__slide item bg-cyan"></div>
      <div class="keen-slider__slide circle bg-navy"></div>
      <div class="keen-slider__slide circle bg-light-gray"></div>
      <div class="keen-slider__slide circle bg-cyan"></div>
      <div class="keen-slider__slide circle bg-navy"></div>
      <div class="keen-slider__slide circle bg-light-gray"></div>
    </div>
  </div>
</template>

<script>
import KeenSlider from "keen-slider";

const animation = { duration: 10000, easing: (t) => t };

export default {
  name: "ChainSearch",
  mounted() {
    this.slider = new KeenSlider(this.$refs.slider, {
      loop: true,
      renderMode: "performance",
      drag: false,
      slides: {
        perView: 6,
      },
      created(s) {
        s.moveToIdx(5, true, animation);
      },
      updated(s) {
        s.moveToIdx(s.track.details.abs + 5, true, animation);
      },
      animationEnded(s) {
        s.moveToIdx(s.track.details.abs + 5, true, animation);
      },
    });
  },
  beforeDestroy() {
    if (this.slider) this.slider.destroy();
  },
};
</script>

<style>
#chain-search {
  height: 100vh;
}
.item {
  width: 300px;
  height: 300px;
  border-radius: 100%;
}
</style>
