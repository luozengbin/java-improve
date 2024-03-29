// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package com.akira;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.Util;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.RemarshalException;
import org.omg.CORBA.portable.ResponseHandler;
import org.omg.CORBA.portable.ServantObject;


public class _HelloWorld_Stub extends Stub implements HelloWorld {
    
    private static final String[] _type_ids = {
        "RMI:com.akira.HelloWorld:0000000000000000"
    };
    
    public String[] _ids() { 
        return _type_ids;
    }
    
    public String sayHelloWorld() throws java.rmi.RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    OutputStream out = _request("sayHelloWorld", true);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (String) in.read_value(String.class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String $_id = in.read_string();
                    throw new UnexpectedException($_id);
                } catch (RemarshalException ex) {
                    return sayHelloWorld();
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("sayHelloWorld",HelloWorld.class);
            if (so == null) {
                return sayHelloWorld();
            }
            try {
                return ((HelloWorld)so.servant).sayHelloWorld();
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
}
