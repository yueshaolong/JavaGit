package design_pattern.chain.bidirectionalchain;

//定义FaceFilter
public class FaceFilter implements Filter {

    public void doFilter(Request request, Response response, FilterChain chain) {

        //将字符串中出现的":):"转换成"^V^";
        request.requestStr = request.requestStr.replace(":):", "^V^")
                //后面添加的是便于我们观察代码执行步骤的字符串
                + "----FaceFilter()";
        System.out.println("requestStr："+request.requestStr);

        chain.doFilter(request, response, chain);

        response.responseStr += "---FaceFilter()";
        System.out.println("responseStr："+response.responseStr);
    }

}
