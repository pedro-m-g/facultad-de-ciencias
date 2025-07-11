<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\CalendarActivity;

class CalendarApiController extends Controller
{

    public function getCalendar()
    {
        return response()->json([
            'data' => CalendarActivity::paginate(),
            'status' => 'success'
        ]);
    }

    public function getActivity(int $id)
    {
        $this->authorize('view', CalendarActivity::class);
        return CalendarActivity::findOrFail($id);
    }
}
