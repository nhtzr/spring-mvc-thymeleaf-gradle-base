package mx.net.nhtzr.base.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.text.MessageFormat;

/**
 * Filtra los peticiones hechas por dispositivos mobiles para mostrar
 * una version especial de las vistas.
 */
public class MobileInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(MobileInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.trace("#postHandle() starts");
        if (modelAndView == null || modelAndView.getViewName() == null) {
            super.postHandle(request, response, handler, modelAndView);
            return;
        }

        final String viewName = modelAndView.getViewName();
        final Device currentDevice = DeviceUtils.getCurrentDevice(request);

        interceptMobile(modelAndView, viewName, currentDevice);
        super.postHandle(request, response, handler, modelAndView);
    }

    private void interceptMobile(ModelAndView modelAndView, String viewName, Device currentDevice) throws Exception {
        if (currentDevice.isMobile()) {
            log.trace("=> {viewVersion : 'mobile'}");
            modelAndView.setViewName("mobile/" + viewName);
            return;
        }

        log.trace("=> {viewVersion : 'web'}");
        modelAndView.setViewName("web/" + viewName);
    }
}
