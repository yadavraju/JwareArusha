package com.jwareconsulting.arusha.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;

/**
 * Created by root on 7/26/16.
 */
public class HorizontalTileView extends RelativeLayout {
    private String title;
    private Drawable icon;
    private int id;
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private static String TAG = HorizontalTileView.class.getSimpleName();
    private View view;


    public HorizontalTileView(Context context) {
        this(context, null);
    }

    public HorizontalTileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalTileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.HorizontalTileView, 0, 0);
        icon = a.getDrawable(R.styleable.HorizontalTileView_tile_icon);
        title = a.getString(R.styleable.HorizontalTileView_tile_title);
        id = a.getInt(R.styleable.HorizontalTileView_id, 0);
        inflate(context);
    }


    private void inflate(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_horizontal_tile, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.layout);
        view = findViewById(R.id.view);
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv_title);
        imageView.setBackground(icon);
        textView.setText(title);

    }

    public void setmListner(final ClickListner mListner) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mListner.OnClicked();

            }
        });

    }


    public interface ClickListner {
        void OnClicked();
    }

}
