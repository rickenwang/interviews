package cos;

public class OKHttp {


    interface Interceptor {

        Response intercept(Chain chain);
    }


    interface Chain {

        // Moderate
        // Aggressive
        // Moderate
        // Aggressive

        Request request();

        Response process(Request request);
    }

    class RealChain implements Chain {

        private Interceptor[] interceptors;
        private int index;
        private Request request;

        public RealChain(Interceptor[] interceptors, int index, Request request) {
            this.interceptors = interceptors;
            this.index = index;
            this.request = request;
        }

        @Override
        public Request request() {
            return request;
        }

        @Override
        public Response process(Request request) {
            RealChain next = new RealChain(interceptors, index + 1, request);
            Interceptor interceptor = interceptors[index];
            return interceptor.intercept(next);
        }
    }

    class Request {}

    class Response {}

}
