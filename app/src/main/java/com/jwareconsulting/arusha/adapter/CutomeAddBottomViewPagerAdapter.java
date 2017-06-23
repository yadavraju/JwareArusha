package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppText;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.rest.response.model.CustomAddResponseModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 9/12/16.
 */
public class CutomeAddBottomViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    public static final String TAG = CutomeAddBottomViewPagerAdapter.class.getSimpleName();
    private OnClickListner onClickListner;
    Context context;
    ArrayList<CustomAddResponseModel.CustomeAddData> dataArrayList;
    CustomAddResponseModel.CustomeAddData customeAddModel;
    LayoutInflater inflater;
    int click_position;
    //    HashMap<String, ArrayList<String>> imageUrls = new HashMap<>();
    ArrayList<CustomAddResponseModel.CustomeAddData> homeList;
    ArrayList<CustomAddResponseModel.CustomeAddData> directoryList;
    ArrayList<CustomAddResponseModel.CustomeAddData> eventList;
    ArrayList<CustomAddResponseModel.CustomeAddData> eventDetailList;
    ArrayList<CustomAddResponseModel.CustomeAddData> directoryDetailList;
    ArrayList<CustomAddResponseModel.CustomeAddData> TopThingstolList;
    ArrayList<CustomAddResponseModel.CustomeAddData> TopThingstolDetailList;
    ArrayList<CustomAddResponseModel.CustomeAddData> subCategoryList;
    ArrayList<CustomAddResponseModel.CustomeAddData> displayList;
    ArrayList<CustomAddResponseModel.CustomeAddData> exchangeRate;
    ArrayList<CustomAddResponseModel.CustomeAddData> map;
    ArrayList<CustomAddResponseModel.CustomeAddData> mapDetail;
    private String title = AppText.KEY_ADS_HOME;


    public CutomeAddBottomViewPagerAdapter(Context context, ArrayList<CustomAddResponseModel.CustomeAddData> mScore) {
        this.context = context;
        this.dataArrayList = mScore;
        homeList = new ArrayList<>();
        eventList = new ArrayList<>();
        directoryList = new ArrayList<>();
        subCategoryList = new ArrayList<>();
        eventDetailList = new ArrayList<>();
        displayList = new ArrayList<>();
        directoryDetailList = new ArrayList<>();
        TopThingstolList = new ArrayList<>();
        TopThingstolDetailList = new ArrayList<>();
        exchangeRate = new ArrayList<>();
        map = new ArrayList<>();
        mapDetail = new ArrayList<>();

        for (int i = 0; i < dataArrayList.size(); i++) {

            String title = dataArrayList.get(i).getCustomeFieldAdddisplayPage();

            if (title.equalsIgnoreCase(AppText.KEY_ADS_HOME)) {
                homeList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_ADS_EVENT)) {
                eventList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_ADS_DIRECTORY_CATEGORY_LIST)) {
                directoryList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.DIRECTORY_DETAIL)) {
                directoryDetailList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.EVENT_DETAIL)) {
                eventDetailList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_ADS_DIRECTORY_SUB_CATEGORY_LIST)) {
                subCategoryList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_THINGS_TO_DO_LIST)) {
                TopThingstolList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_THINGS_TO_DO_DETAIL_LIST)) {
                TopThingstolDetailList.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_EXCHANGE_RATE)) {
                exchangeRate.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_MAP)) {
                map.add(dataArrayList.get(i));
            } else if (title.equalsIgnoreCase(AppText.KEY_MAP_DETAIL)) {
                mapDetail.add(dataArrayList.get(i));
            }


        }


    }

    @Override
    public int getCount() {
        if (displayList.size() == 0) {
            return 1;
        } else {
            return displayList.size();
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.custome_add_viewpager_layout, container,
                false);
        //customeAddModel = dataArrayList.get(position);
        // Locate the TextViews in viewpager_item.xml
        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_custom_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListner != null) {


                    try {
                        String url = displayList.get(position).getAdsUrl();
                        if (!ValidationUtils.isEmpty(url)) {
                            onClickListner.onClick(url);
                        }


                    } catch (Exception e) {
                        AppLog.printStackStrace(e);
                    }

                }

            }
        });
        if (displayList.size() == 0) {
            Picasso.with(context)
                    .load(R.drawable.header_offline_add)
                    .into(imageView);
        } else {
            Picasso.with(context)
                    .load(displayList.get(position).getCustomeAddImage())
                    .into(imageView);
        }
        final int page = position;

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }

    public void onPageChanged(String title) {
//        this.title=title;
//        notifyDataSetChanged();

        if (title.equalsIgnoreCase(AppText.KEY_ADS_HOME)) {
            displayList.clear();
            displayList.addAll(homeList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_ADS_EVENT)) {
            displayList.clear();
            displayList.addAll(eventList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_ADS_DIRECTORY_CATEGORY_LIST)) {
            displayList.clear();
            displayList.addAll(directoryList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.DIRECTORY_DETAIL)) {
            displayList.clear();
            displayList.addAll(directoryDetailList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.EVENT_DETAIL)) {
            displayList.clear();
            displayList.addAll(eventDetailList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_ADS_DIRECTORY_SUB_CATEGORY_LIST)) {
            displayList.clear();
            displayList.addAll(subCategoryList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_THINGS_TO_DO_LIST)) {
            displayList.clear();
            displayList.addAll(TopThingstolList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_THINGS_TO_DO_DETAIL_LIST)) {
            displayList.clear();
            displayList.addAll(TopThingstolDetailList);
            notifyDataSetChanged();
        } else if (title.equalsIgnoreCase(AppText.KEY_EXCHANGE_RATE)) {
            displayList.clear();
            displayList.addAll(exchangeRate);
            notifyDataSetChanged();
        }

    }


    public interface OnClickListner {

        public void onClick(String url);
    }

    public void setOnClickListner(OnClickListner onClickListner) {
        this.onClickListner = onClickListner;
    }
}


