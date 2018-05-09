package finance.doctype;

public abstract class AbstractDocumentType implements DocumentType, DocumentEntity {
    private String date;
    private float sum;

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public float getSum() {
        return this.sum;
    }

    @Override
    public void setSum(float sum) {
        this.sum = sum;
    }

    @Override
    public String getDate() {
        return date;
    }
}
