package tool.SoftwareGenerator;

abstract class AbstractSoftwareDesignFactory<T> {
    abstract T getDesign(String design);
}
