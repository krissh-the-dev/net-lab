int hdr_aodv::offset_;
static class aodvHeaderClass : public PacketHeaderClass {
  public: aodvHeaderClass() : PacketHeaderClass("PacketHeader/aodv", sizeof(hdr_all_aodv)) {
          bind_offset(&hdr_aodv::offset_);
  }
} class_rtProtoaodv_hdr;

static class aodvclass : public TclClass {
  public:
    aodvclass() : TclClass("Agent/aodv") {}
    TclObject* create(int argc, const char*const* argv) {
        assert(argc == 5);
        return (new aodv((nsaddr_t) Address::instance().str2addr(argv[4])));
    }
} class_rtProtoaodv;


int
aodv::command(int argc, const char*const* argv) {
    if(argc == 2) {
        Tcl& tcl = Tcl::instance();

        if(strncasecmp(argv[1], "id", 2) == 0) {
            tcl.resultf("%d", index);
            return TCL_OK;
        }
        if (strncasecmp(argv[1], "dump-table", 10) == 0) {
            printf("Node %d: Route table:\n", index);
            rtable.rt_dumptable();
            return TCL_OK;
        }
        if(strncasecmp(argv[1], "start", 2) == 0) {
            btimer.handle((Event*) 0);

#ifndef aodv_LINK_LAYER_DETECTION
            htimer.handle((Event*) 0);
            ntimer.handle((Event*) 0);
#endif
            rtimer.handle((Event*) 0);
            return TCL_OK;
        }
