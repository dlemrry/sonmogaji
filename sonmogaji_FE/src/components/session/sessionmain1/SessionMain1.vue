<template>
  <b-container>
    <div>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="6000"
        controls
        indicators
        background="#ffffff"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <template #img> <CarouselSlide1 /></template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img> <CarouselSlide2 /></template>
        </b-carousel-slide>
      </b-carousel>
    </div>
    
  </b-container>
</template>

<script>
import CarouselSlide1 from "./CarouselSlide1";
import CarouselSlide2 from "./CarouselSlide2";
export default {
  name: "SessionMain1",
  components: {
    CarouselSlide1,
    CarouselSlide2,
  },
  created() {
    console.log('sessionMain1')
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
  },
};
</script>
